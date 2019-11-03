package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.entity.FactorEntity;
import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.entity.TeamEntity;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.IDataEvent;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataEvent implements IDataEvent {


    private static final Logger log = LoggerFactory.getLogger(DataEvent.class);
    private static volatile IDataEvent instance;

    public static IDataEvent getInstance() {
        IDataEvent localInstance = instance;
        if (localInstance == null) {
            synchronized (IDataEvent.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataEvent();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Long addEvent(EventDTO event) {
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            EventEntity eventEntity = EntityDtoViewConverter.getEntity(event);
            LeagueEntity leagueEntity = session.find(LeagueEntity.class, event.getLeagueId());
            leagueEntity.getEvents().add(eventEntity);
            eventEntity.setLeague(leagueEntity);
            Long id = (Long) session.save(eventEntity);
            for (FactorEntity factor : eventEntity.getFactors()) {
                factor.setEvent(eventEntity);
                session.save(factor);
            }
            session.getTransaction().commit();
            return id;
        } catch (RollbackException e) {
            log.error("AddEvent exception, EventDTO {}", event);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        throw new RuntimeException();
    }

    @Override
    public boolean eventIsExist(EventDTO event) {
        Session session = HibernateUtil.getSession();
        boolean result = false;
        try {
            session.getTransaction().begin();
            final Query query = session.createQuery("FROM EventEntity e where e.teamOneId = :teamOneId AND e.teamTwoId = :teamTwoId AND e.startTime = :startTime")
                    .setParameter("teamOneId", event.getTeamOne())
                    .setParameter("teamTwoId", event.getTeamTwo())
                    .setParameter("startTime", event.getStartTime());
            List<EventEntity> entity = query.list();
            session.getTransaction().commit();
            if (!entity.isEmpty()) {
                result = true;
            }
            return result;
        } catch (RollbackException e) {
            log.error("eventIsExist exception, EventDTO {}", event);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        throw new RuntimeException();
    }

    @Override
    public List<EventDTO> getAllNotFinishedEvents() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<EventEntity> eventEntities = session.createQuery("FROM EventEntity e WHERE e.resultFactorId = null").list();
        List<EventDTO> views = new ArrayList<>();
        for (EventEntity entity : eventEntities) {
            views.add(EntityDtoViewConverter.getDTO(entity));
        }
        session.getTransaction().commit();
        return views;
    }

    @Override
    public EventDTO getSavedEventById(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<EventEntity> criteria = criteriaBuilder.createQuery(EventEntity.class);
        Root<EventEntity> event = criteria.from(EventEntity.class);
        criteria.select(event)
                .where(criteriaBuilder.equal(event.get("id"),id));
        EventEntity eventEntity = session.createQuery(criteria).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return EntityDtoViewConverter.getDTO(eventEntity);
    }

    @Override
    public Long getCountEvents() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Long countEvents = session.createQuery("SELECT count (*) from EventEntity", Long.class).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return countEvents;
    }

    @Override
    public List<EventDTO> getEventsOnPage(int page, int pageSize) {
        List<EventDTO>events = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            final Query query = session.createQuery("FROM EventEntity e ORDER BY e.id asc")
                    .setMaxResults(pageSize)
                    .setFirstResult((page - 1)*pageSize)
                    .setReadOnly(true);
            List<EventEntity> eventEntities = query.list();
            for (EventEntity entity : eventEntities) {
                EventDTO dto = EntityDtoViewConverter.getDTO(entity);
                events.add(dto);
            }
            session.getTransaction().commit();
        }catch (PersistenceException e){
            log.error("Fail to get list of events from DB at: {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return events;
    }


    @Override
    public List<LeagueDTO> getAllLeagues() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM LeagueEntity");
        query.setCacheable(true);
        List<LeagueEntity> leagueEn = (List<LeagueEntity>) query.list();
        session.getTransaction().commit();
        session.close();
        List<LeagueDTO> leaguesDTO = new ArrayList<>();
        for (LeagueEntity entity : leagueEn) {
            leaguesDTO.add(EntityDtoViewConverter.getDTO(entity));
        }
        return leaguesDTO;
    }

    @Override
    public List<TeamDTO> getAllTeamsByLeague(Long idLeague) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        LeagueEntity leagueEntity = session.find(LeagueEntity.class, idLeague);
        Hibernate.initialize(leagueEntity.getTeams());
        List<TeamEntity> teams = leagueEntity.getTeams();
        session.getTransaction().commit();
        session.close();
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (TeamEntity team : teams) {
            teamDTOS.add(EntityDtoViewConverter.getDTO(team));
        }
        return teamDTOS;
    }

//    public boolean saveLeagueTeam(){
//        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
//        LeagueEntity leagueEntity = new LeagueEntity();
//        leagueEntity.setEvents(new ArrayList<>());
//        leagueEntity.setTeams(new ArrayList<>());
//        leagueEntity.setName("UFC");
//        session.getTransaction().begin();
//        TeamEntity teamEntity = session.find(TeamEntity.class, "Arsenal");
//        leagueEntity.getTeams().add(teamEntity);
//        teamEntity.getLeagues().add(leagueEntity);
//        session.saveOrUpdate(leagueEntity);
//        session.getTransaction().commit();
//        session.close();
//        return true;
//    }
//
//    public boolean deleteLeague(){
//        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
//        session.getTransaction().begin();
//        LeagueEntity leagueEntity = session.find(LeagueEntity.class, 3L);
//        Hibernate.initialize(leagueEntity.getTeams());
//        Hibernate.initialize(leagueEntity.getEvents());
//        session.delete(leagueEntity);
//        session.getTransaction().commit();
//        session.close();
//        return true;
//    }
}