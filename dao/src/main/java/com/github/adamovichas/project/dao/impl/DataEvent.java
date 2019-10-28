package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.entity.FactorEntity;
import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.entity.TeamEntity;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.IDataConnect;
import com.github.adamovichas.project.IDataEvent;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

public enum DataEvent implements IDataEvent {
    DATA_EVENT;

    private static final Logger log = LoggerFactory.getLogger(DataEvent.class);
    private IDataConnect CONNECTION;

    DataEvent() {
        CONNECTION = DataConnect.CONNECT;
    }

    @Override
    public Long addEvent(EventDTO event) {
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        try {
            session.getTransaction().begin();
            EventEntity eventEntity = EntityDtoViewConverter.getEntity(event);
            Long id = (Long) session.save(eventEntity);
//            TeamEntity teamOne = session.find(TeamEntity.class, eventEntity.getTeamOneId());
//            TeamEntity teamTwo = session.find(TeamEntity.class, eventEntity.getTeamTwoId());
//            teamOne.getEvents().add(eventEntity);
//            teamTwo.getEvents().add(eventEntity);
//            eventEntity.getTeams().add(teamOne);
//            eventEntity.getTeams().add(teamTwo);
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
                    .setParameter("teamOneId", event.getTeamOneId())
                    .setParameter("teamTwoId", event.getTeamTwoId())
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
    public List<EventView> getAllNotFinishedEvents() {
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        session.getTransaction().begin();
        List<EventEntity> eventEntities = session.createQuery("FROM EventEntity e WHERE e.resultFactorId = null").list();
        List<EventView> views = new ArrayList<>();
        String teamOne;
        String teamtwo;
        for (EventEntity entity : eventEntities) {
            teamOne = session.find(TeamEntity.class, entity.getTeamOneId()).getName();
            teamtwo = session.find(TeamEntity.class, entity.getTeamTwoId()).getName();
            views.add(EntityDtoViewConverter.getView(entity, teamOne, teamtwo));
        }
        session.getTransaction().commit();
        return views;
    }

    @Override
    public EventView getSavedEventById(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.getTransaction().begin();
        EventEntity eventEntity = session.find(EventEntity.class, id);
        Hibernate.initialize(eventEntity.getFactors());
        String teamOneName = session.find(TeamEntity.class, eventEntity.getTeamOneId()).getName();
        String teamTwoName = session.find(TeamEntity.class, eventEntity.getTeamTwoId()).getName();
        session.getTransaction().commit();
        session.close();
        return EntityDtoViewConverter.getView(eventEntity, teamOneName, teamTwoName);
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