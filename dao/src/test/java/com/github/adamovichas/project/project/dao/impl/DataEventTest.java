package com.github.adamovichas.project.project.dao.impl;

import com.github.adamovichas.project.web.dto.EventView;
import com.github.adamovichas.project.web.dto.League;
import com.github.adamovichas.project.web.dto.Team;
import com.github.adamovichas.project.web.event.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class DataEventTest {

    private DataEvent dataEvent = DataEvent.DATA_EVENT;
    private UtilTest util = UtilTest.UTIL_TEST;


    @Test
    public void addEvent(){
        Event eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        assertNotNull(id);
        util.deleteEvent(id);
    }

    @Test
    public void eventIsExist(){
        Event eventTest = util.createEventTest();
        Event eventNull = dataEvent.eventIsExist(eventTest);
        assertNull(eventNull);
        Long id = dataEvent.addEvent(eventTest);
        Event event = dataEvent.eventIsExist(eventTest);
        assertNotNull(event);
        util.deleteEvent(id);
    }

    @Test
    public void getSavedEventById(){
        Event eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        EventView savedEventById = dataEvent.getSavedEventById(id);
        assertNotNull(savedEventById);
        util.deleteEvent(id);
    }

    @Test
    void getAllNotFinishedEvents(){
        int countAllNotFinishedEvents = util.getCountAllNotFinishedEvents();
        final List<EventView> events = dataEvent.getAllNotFinishedEvents();
        assertEquals(events.size(),countAllNotFinishedEvents);
    }

    @Test
    void getAllLeagues(){
        int countLeagues = 1;
        List<League> allLeagues = dataEvent.getAllLeagues();
        assertEquals(countLeagues,allLeagues.size());
    }

    @Test
    void getAllTeamsByLeague(){
        Long idLeague = 1L;
        int countTeamsInLeague = 20;
        List<Team> allTeamsByLeague = dataEvent.getAllTeamsByLeague(idLeague);
        assertEquals(countTeamsInLeague,allTeamsByLeague.size());
    }
}
