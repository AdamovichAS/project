package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class DataEventTest {

    private DataEvent dataEvent = (DataEvent) DataEvent.getInstance();
    private Util util = Util.UTIL_TEST;
    private static EntityManager entityManager;

    @BeforeAll
    static void init() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @AfterAll
    public static void cleanUp() {
        entityManager.close();
    }

    @Test
    public void addEvent(){
        EventDTO eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        assertNotNull(id);
        util.deleteEvent(id);
    }

    @Test
    public void eventIsExistNull(){
        EventDTO eventTest = util.createEventTest();
        boolean result = dataEvent.eventIsExist(eventTest);
        assertFalse(result);

    }

    @Test
    public void eventIsExist(){
        EventDTO eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        boolean result = dataEvent.eventIsExist(eventTest);
        assertTrue(result);
        util.deleteEvent(id);
    }

    @Test
    public void getSavedEventById(){
        EventDTO eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        EventDTO savedEventById = dataEvent.getSavedEventById(id);
        util.deleteEvent(id);
        assertNotNull(savedEventById);
        assertEquals(savedEventById.getName(),"Arsenal - Aston Vila");
    }

    @Test
    void getAllNotFinishedEvents(){
        Long countAllNotFinishedEvents = util.getCountAllNotFinishedEvents();
        final List<EventDTO> events = dataEvent.getAllNotFinishedEvents();
        assertEquals(events.size(),countAllNotFinishedEvents);
    }

    @Test
    void getAllLeagues(){
        int countLeagues = 2;
        List<LeagueDTO> allLeagues = dataEvent.getAllLeagues();
        assertEquals(countLeagues,allLeagues.size());
    }

    @Test
    void getAllTeamsByLeague(){
        Long idLeague = 1L;
        int countTeamsInLeague = 20;
        List<TeamDTO> allTeamsByLeague = dataEvent.getAllTeamsByLeague(idLeague);
        assertEquals(countTeamsInLeague,allTeamsByLeague.size());
    }
}
