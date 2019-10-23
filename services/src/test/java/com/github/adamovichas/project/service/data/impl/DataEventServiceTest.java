package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.dto.League;
import com.github.adamovichas.project.model.dto.Team;
import com.github.adamovichas.project.entity.Event;
import com.github.adamovichas.project.model.event.Factor;
import com.github.adamovichas.project.model.event.FactorName;
import com.github.adamovichas.project.dao.impl.DataEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class DataEventServiceTest {

    @Mock
    public DataEvent dataEvent;

    @InjectMocks
    public DataEventService dataEventService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }


    Event createEventTest(){
        Event event = new Event(1L,2L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<Factor> factors= new ArrayList<>();
        event.setId(50L);
        factors.add(new Factor(FactorName.win,2.5));
        factors.add(new Factor(FactorName.draw,3));
        factors.add(new Factor(FactorName.lose,2.1));
        event.setFactors(factors);
        return event;
    }

    @Test
    public void eventIsExist(){
        Event eventTest = createEventTest();
        dataEventService.eventIsExist(eventTest);
        Mockito.verify(dataEvent,times(1)).eventIsExist(eventTest);
    }

    @Test
    public void addEvent(){
        Event eventTest = createEventTest();
        dataEventService.addEvent(eventTest);
        Mockito.verify(dataEvent,times(1)).addEvent(eventTest);
    }

    @Test
    public void getAllNotFinishedEvents(){
        List<EventView>eventViews = new ArrayList<>(Arrays.asList(new EventView(),new EventView(),new EventView()));
        when(dataEvent.getAllNotFinishedEvents()).thenReturn(eventViews);
        List<EventView> allNotFinishedEvents = dataEventService.getAllNotFinishedEvents();
        assertEquals(allNotFinishedEvents.size(),eventViews.size());
    }

    @Test
    public void getAllLeagues(){
        List<League>leagues = new ArrayList<>(Arrays.asList(new League(),new League(),new League()));
        when(dataEvent.getAllLeagues()).thenReturn(leagues);
        List<League> allLeagues = dataEventService.getAllLeagues();
        assertEquals(allLeagues.size(),leagues.size());
    }

    @Test
    public void getAllTeamsByLeague(){
        List<Team>teams = new ArrayList<>(Arrays.asList(new Team(12L,"Test"),new Team(13L,"Test")));
        when(dataEvent.getAllTeamsByLeague(Mockito.any())).thenReturn(teams);
        List<Team> allTeamsByLeague = dataEventService.getAllTeamsByLeague(10L);
        assertEquals(allTeamsByLeague.size(),teams.size());
    }

    @Test
    public void getEventById(){
        EventView eventTest = new EventView();
        eventTest.setId(10L);
        when(dataEvent.getSavedEventById(eventTest.getId())).thenReturn(eventTest);
        EventView eventById = dataEventService.getEventById(10L);
        assertEquals(eventById.getId(),eventTest.getId());
    }
}
