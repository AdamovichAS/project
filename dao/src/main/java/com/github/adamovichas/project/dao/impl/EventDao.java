package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IEventDao;
import com.github.adamovichas.project.dao.repository.EventRepository;
import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class EventDao implements IEventDao {


    private static final Logger log = LoggerFactory.getLogger(EventDao.class);

    private final EventRepository repository;

    public EventDao(EventRepository repository) {
        this.repository = repository;
    }


    @Override
    public Long addEvent(EventDTO event) {
        EventEntity eventEntity = EntityDtoViewConverter.getEntity(event);
        LeagueEntity league = repository.getLeague(event.getLeagueId());
        eventEntity.setLeague(league);
        league.getEvents().add(eventEntity);
        repository.save(eventEntity);
        return eventEntity.getId();
    }

    @Override
    public boolean eventIsExist(EventDTO event) {
//        Session session = repository.getSession();
//        boolean result = false;
//        try {
//            session.getTransaction().begin();
//            final Query query = session.createQuery("FROM EventEntity e where e.teamOneId = :teamOneId AND e.teamTwoId = :teamTwoId AND e.startTime = :startTime")
//                    .setParameter("teamOneId", event.getTeamOne())
//                    .setParameter("teamTwoId", event.getTeamTwo())
//                    .setParameter("startTime", event.getStartTime());
//            List<EventEntity> entity = query.list();
//            session.getTransaction().commit();
//            if (!entity.isEmpty()) {
//                result = true;
//            }
//            return result;
//        } catch (RollbackException e) {
//            log.error("eventIsExist exception, EventDTO {}", event);
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }

        return repository.existsByTeamOneIdAndTeamTwoIdAndStartTime(event.getTeamOne(),event.getTeamTwo(),event.getStartTime());
    }

    @Override
    public List<EventDTO> getAllNotFinishedEvents() {
//        Session session = repository.getSession();
//        session.getTransaction().begin();
//        List<EventEntity> eventEntities = session.createQuery("FROM EventEntity e WHERE e.resultFactorId = null").list();
//        List<EventDTO> views = new ArrayList<>();
//        for (EventEntity entity : eventEntities) {
//            views.add(EntityDtoViewConverter.getDTO(entity));
//        }
//        session.getTransaction().commit();
//        return views;
        List<EventEntity> eventEntities = repository.getAllByResultFactorIdIsNull();
        return getDTOs(eventEntities);
    }

    @Override
    public EventDTO getEventById(Long id) {
//        Session session = repository.getSession();
//        session.getTransaction().begin();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<EventEntity> criteria = criteriaBuilder.createQuery(EventEntity.class);
//        Root<EventEntity> event = criteria.from(EventEntity.class);
//        criteria.select(event)
//                .where(criteriaBuilder.equal(event.get("id"),id));
//        EventEntity eventEntity = session.createQuery(criteria).getSingleResult();
//        session.getTransaction().commit();
//        session.close();
//        return EntityDtoViewConverter.getDTO(eventEntity);
         EventEntity eventEntity = repository.findById(id).get();
         return EntityDtoViewConverter.getDTO(eventEntity);
    }

    @Override
    public Long getCountEvents() {
//        Session session = repository.getSession();
//        session.getTransaction().begin();
//        Long countEvents = session.createQuery("SELECT count (*) from EventEntity", Long.class).getSingleResult();
//        session.getTransaction().commit();
//        session.close();
//        return countEvents;
        return repository.count();
    }

    @Override
    public List<EventDTO> getEventsOnPage(int page, int pageSize) {
        List<EventDTO>events = new ArrayList<>();
//        Session session = repository.getSession();
//        try {
//            session.getTransaction().begin();
//            final Query query = session.createQuery("FROM EventEntity e ORDER BY e.id asc")
//                    .setMaxResults(pageSize)
//                    .setFirstResult((page - 1)*pageSize)
//                    .setReadOnly(true);
//            List<EventEntity> eventEntities = query.list();
//            for (EventEntity entity : eventEntities) {
//                EventDTO dto = EntityDtoViewConverter.getDTO(entity);
//                events.add(dto);
//            }
//            session.getTransaction().commit();
//        }catch (PersistenceException e){
//            log.error("Fail to get list of events from DB at: {}", LocalDateTime.now(), e);
//            throw new RuntimeException(e);
//        }finally {
//            session.close();
//        }
        List<EventEntity> eventEntities = repository.findAll(PageRequest.of(page - 1, pageSize, Sort.by("startTime"))).toList();
        return getDTOs(eventEntities);
    }


    private List<EventDTO> getDTOs(List<EventEntity> entities){
        List<EventDTO>dtos = new ArrayList<>();
        for (EventEntity entity : entities) {
            dtos.add(EntityDtoViewConverter.getDTO(entity));
        }
        return dtos;
    }
    @Override
    public List<LeagueDTO> getAllLeagues() {
//        Session session = repository.getSession();
//        session.beginTransaction();
//        Query query = session.createQuery("FROM LeagueEntity");
//        query.setCacheable(true);
//        List<LeagueEntity> leagueEn = (List<LeagueEntity>) query.list();
//        session.getTransaction().commit();
//        session.close();
//        List<LeagueDTO> leaguesDTO = new ArrayList<>();
//        for (LeagueEntity entity : leagueEn) {
//            leaguesDTO.add(EntityDtoViewConverter.getDTO(entity));
//        }
//        return leaguesDTO;
        throw new RuntimeException();
    }

    @Override
    public List<TeamDTO> getAllTeamsByLeague(Long idLeague) {
//        Session session = repository.getSession();
//        session.getTransaction().begin();
//        LeagueEntity leagueEntity = session.find(LeagueEntity.class, idLeague);
//        Hibernate.initialize(leagueEntity.getTeams());
//        List<TeamEntity> teams = leagueEntity.getTeams();
//        session.getTransaction().commit();
//        session.close();
//        List<TeamDTO> teamDTOS = new ArrayList<>();
//        for (TeamEntity team : teams) {
//            teamDTOS.add(EntityDtoViewConverter.getDTO(team));
//        }
//        return teamDTOS;
        throw new RuntimeException();
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