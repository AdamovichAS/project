package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.dao.DataEvent;
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


    EventDTO createEventTest(){
        EventDTO eventDTO = new EventDTO("Arsenal","Liverpool",1L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<FactorDTO> factorDTOS = new ArrayList<>();
        eventDTO.setId(50L);
        factorDTOS.add(new FactorDTO(FactorName.win,2.5));
        factorDTOS.add(new FactorDTO(FactorName.draw,3));
        factorDTOS.add(new FactorDTO(FactorName.lose,2.1));
        eventDTO.setFactors(factorDTOS);
        return eventDTO;
    }

    @Test
    public void eventIsExist(){
        EventDTO eventDTOTest = createEventTest();
        dataEventService.eventIsExist(eventDTOTest);
        Mockito.verify(dataEvent,times(1)).eventIsExist(eventDTOTest);
    }

    @Test
    public void addEvent(){
        EventDTO eventDTOTest = createEventTest();
        dataEventService.addEvent(eventDTOTest);
        Mockito.verify(dataEvent,times(1)).addEvent(eventDTOTest);
    }

    @Test
    public void getAllNotFinishedEvents(){
        List<EventDTO> eventDTOS = new ArrayList<>(Arrays.asList(new EventDTO(),new EventDTO(),new EventDTO()));
        when(dataEvent.getAllNotFinishedEvents()).thenReturn(eventDTOS);
        List<EventDTO> allNotFinishedEvents = dataEventService.getAllNotFinishedEvents();
        assertEquals(allNotFinishedEvents.size(), eventDTOS.size());
    }

    @Test
    public void getAllLeagues(){
        List<LeagueDTO> leagueDTOS = new ArrayList<>(Arrays.asList(new LeagueDTO(),new LeagueDTO(),new LeagueDTO()));
        when(dataEvent.getAllLeagues()).thenReturn(leagueDTOS);
        List<LeagueDTO> allLeagueDTOS = dataEventService.getAllLeagues();
        assertEquals(allLeagueDTOS.size(), leagueDTOS.size());
    }

    @Test
    public void getAllTeamsByLeague(){
        List<TeamDTO> teamDTOS = new ArrayList<>(Arrays.asList(new TeamDTO("Liverpool"),new TeamDTO("Arsenal")));
        when(dataEvent.getAllTeamsByLeague(Mockito.any())).thenReturn(teamDTOS);
        List<TeamDTO> allTeamsByLeague = dataEventService.getAllTeamsByLeague(10L);
        assertEquals(allTeamsByLeague.size(), teamDTOS.size());
    }

    @Test
    public void getEventById(){
        EventDTO eventTest = new EventDTO();
        eventTest.setId(10L);
        when(dataEvent.getSavedEventById(eventTest.getId())).thenReturn(eventTest);
        EventDTO eventById = dataEventService.getEventById(10L);
        assertEquals(eventById.getId(),eventTest.getId());
    }

    @Test
    public void getEventMaxPages(){
        when(dataEvent.getCountEvents()).thenReturn(10L);
        Long pages = dataEventService.getEventMaxPages();
        assertEquals(pages,2L);
    }
}
