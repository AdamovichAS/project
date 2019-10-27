package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class DataEventDTOTest {

    private DataEvent dataEvent = DataEvent.DATA_EVENT;
    private UtilTest util = UtilTest.UTIL_TEST;


    @Test
    public void addEvent(){
        EventDTO eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        assertNotNull(id);
        util.deleteEvent(id);
    }

    @Test
    public void eventIsExist(){
        EventDTO eventTest = util.createEventTest();
        EventDTO eventNull = dataEvent.eventIsExist(eventTest);
        assertNull(eventNull);
        Long id = dataEvent.addEvent(eventTest);
        EventDTO event = dataEvent.eventIsExist(eventTest);
        assertNotNull(event);
        util.deleteEvent(id);
    }

    @Test
    public void getSavedEventById(){
        EventDTO eventTest = util.createEventTest();
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
