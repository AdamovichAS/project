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
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        List<EventView> result = new ArrayList<>();
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(
                     "SELECT e.id as id, t.name as team_one, t2.name as team_two, e.start_time, e.end_time, fe1.name as factor1,fe1.value as factor1_val, fe2.name as factor2, fe2.value as factor2_val, fe3.name as factor3,fe3.value as factor3_val FROM factor_event fe1\n" +
                             "LEFT JOIN event e on fe1.event_id = e.id\n" +
                             "LEFT JOIN team t on e.team_one = t.id\n" +
                             "LEFT JOIN team t2 on e.team_two = t2.id\n" +
                             "LEFT JOIN factor_event fe2 on e.id = fe1.event_id\n" +
                             "LEFT JOIN factor_event fe3 on e.id = fe2.event_id\n" +
                             "WHERE e.result IS NULL AND (fe1.name != fe2.name) AND (fe1.name!= fe3.name) AND (fe2.name != fe3.name) group by e.id;;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                EventView view;
                List<FactorDTO> factors;
                while (resultSet.next()) {
                    view = new EventView();
                    factors = new ArrayList<>();
                    view.setId(resultSet.getLong("id"));
                    String teamTwo = resultSet.getString("team_two");
                    String teamOne = resultSet.getString("team_one");
                    view.setName(String.format("%s - %s", teamOne, teamTwo));
                    view.setStartTime(resultSet.getTimestamp("start_time"));
                    view.setEndTime(resultSet.getTimestamp("end_time"));
                    String factor1 = resultSet.getString("factor1");
                    double factor1Val = resultSet.getDouble("factor1_val");
                    factors.add(new FactorDTO(FactorName.valueOf(factor1), factor1Val));
                    String factor2 = resultSet.getString("factor2");
                    double factor2Val = resultSet.getDouble("factor2_val");
                    factors.add(new FactorDTO(FactorName.valueOf(factor2), factor2Val));
                    String factor3 = resultSet.getString("factor3");
                    double factor3Val = resultSet.getDouble("factor3_val");
                    factors.add(new FactorDTO(FactorName.valueOf(factor3), factor3Val));
                    view.setFactors(factors);
                    result.add(view);
                }
                return result;
            }
        } catch (SQLException e) {
            log.error("GetAllNotFinishedEvent Sql exception");
        }
        throw new RuntimeException();
    }

    @Override
    public EventView getSavedEventById(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.getTransaction().begin();
        EventEntity eventEntity = session.find(EventEntity.class, id);
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
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM LeagueEntity");
            List<LeagueEntity> leagueEn = (List<LeagueEntity>) query.list();
            session.getTransaction().commit();
            List<LeagueDTO> leaguesDTO = new ArrayList<>();
            for (LeagueEntity entity : leagueEn) {
                leaguesDTO.add(EntityDtoViewConverter.getDTO(entity));
            }
            return leaguesDTO;
        } catch (RollbackException e) {
            log.error("GetAllLeagues exception");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        throw new RuntimeException();
    }

    @Override
    public List<TeamDTO> getAllTeamsByLeague(Long idLeague) {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);

        session.getTransaction().begin();
        LeagueEntity leagueEntity = session.find(LeagueEntity.class, idLeague);
        List<TeamEntity> teams = leagueEntity.getTeams();
        session.getTransaction().commit();
        List<TeamDTO> teamDTOS = new ArrayList<>();
        session.close();
        for (TeamEntity team : teams) {
            teamDTOS.add(EntityDtoViewConverter.getDTO(team));
        }
        return teamDTOS;


    }
}