package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.entity.FactorEntity;
import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.entity.TeamEntity;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.IDataConnect;
import com.github.adamovichas.project.IDataEvent;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
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
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        try {
            session.getTransaction().begin();
            EventEntity eventEntity = EntityDtoViewConverter.getEntity(event);
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
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
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
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
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
        EventEntity eventEntity = session.find(EventEntity.class, id);
        Hibernate.initialize(eventEntity.getFactors());
        session.getTransaction().commit();
        session.close();
        return EntityDtoViewConverter.getDTO(eventEntity);
    }


    @Override
    public List<LeagueDTO> getAllLeagues() {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        Query query = session.createQuery("FROM LeagueEntity");
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
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
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
}