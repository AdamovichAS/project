package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.impl.LeagueDao;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.dao.impl.EventDao;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.util.event.EventUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    @Mock
    public EventDao eventDao;

    @Mock
    public LeagueDao leagueDao;

    @Mock
    EventUtil eventUtil;

    @InjectMocks
    public EventService eventService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }


    EventDTO createEventTest(){
        EventDTO eventDTO = new EventDTO("Arsenal","Liverpool",1L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        eventDTO.setId(50L);
        return eventDTO;
    }

    EventView createEventView(){
        EventView view = new EventView();
        view.setId(50L);
        view.setTeamOne("Arsenal");
        view.setTeamTwo("Liverpool");
        view.setLeagueId(1L);
        view.setStartTime( Timestamp.valueOf("2019-12-05 17:00:00"));
        view.setEndTime(Timestamp.valueOf("2019-12-05 18:00:00"));
        List<FactorDTO> factorsTest = createFactorsTest();
        view.setFactors(factorsTest);
        return view;
    }

    List<FactorDTO> createFactorsTest(){
        List<FactorDTO> factorDTOS = new ArrayList<>();
        factorDTOS.add(new FactorDTO(FactorName.win,2.5,50L));
        factorDTOS.add(new FactorDTO(FactorName.draw,3,50L));
        factorDTOS.add(new FactorDTO(FactorName.lose,2.1,50L));
        return factorDTOS;
    }


    @Test
    public void eventIsExist(){
        EventDTO eventDTOTest = createEventTest();
        eventService.eventIsExist(eventDTOTest);
        Mockito.verify(eventDao,times(1)).eventIsExist(eventDTOTest);
    }

    @Test
    public void addEvent(){
        EventDTO eventDTOTest = createEventTest();
        eventService.addEvent(eventDTOTest);
        Mockito.verify(eventDao,times(1)).addEvent(eventDTOTest);
    }

    @Test
    public void getAllNotFinishedEvents(){
        List<EventView> views = new ArrayList<>(Collections.singletonList(createEventView()));
        when(eventDao.getAllNotFinishedEvents()).thenReturn(views);
        List<EventView> allNotFinishedEvents = eventService.getAllNotFinishedEvents();
        assertEquals(allNotFinishedEvents.size(), views.size());
    }

    @Test
    public void getAllLeagues(){
        List<LeagueDTO> leagueDTOS = new ArrayList<>(Arrays.asList(new LeagueDTO(),new LeagueDTO(),new LeagueDTO()));
        when(leagueDao.getAllLeagues()).thenReturn(leagueDTOS);
        List<LeagueDTO> allLeagueDTOS = eventService.getAllLeagues();
        assertEquals(allLeagueDTOS.size(), leagueDTOS.size());
    }

    @Test
    public void getAllTeamsByLeague(){
        List<TeamDTO> teamDTOS = new ArrayList<>(Arrays.asList(new TeamDTO("Liverpool"),new TeamDTO("Arsenal")));
        when(leagueDao.getAllTeamsByLeague(Mockito.any())).thenReturn(teamDTOS);
        List<TeamDTO> allTeamsByLeague = eventService.getAllTeamsByLeague(10L);
        assertEquals(allTeamsByLeague.size(), teamDTOS.size());
    }

    @Test
    public void getEventById(){
        EventDTO eventTest = new EventDTO();
        eventTest.setId(10L);
        when(eventDao.getEventById(eventTest.getId())).thenReturn(eventTest);
        EventDTO eventById = eventService.getEventById(10L);
        assertEquals(eventById.getId(),eventTest.getId());
    }

    @Test
    public void getEventMaxPages(){
        when(eventDao.getCountEvents()).thenReturn(10L);
        Long pages = eventService.getEventMaxPages();
        assertEquals(pages,2L);
    }

    @Test
    void getEventViewById(){
        eventService.getEventViewById(10L);
        verify(eventDao,times(1)).getEventViewById(10L);
    }

    @Test
    void getEventMaxPagesByResultFactorId(){
        when(eventDao.getCountEventsByResultFactorId(true)).thenReturn(10L);
        final Long pages = eventService.getEventMaxPagesByResultFactorId(true);
        assertEquals(pages,2);
    }

    @Test
    void makeEventFinished(){
        EventDTO eventTest = createEventTest();
        EventStatisticDTO statisticDTO = new EventStatisticDTO();
        statisticDTO.setEventId(eventTest.getId());
        statisticDTO.setTeamOneGoals(1);
        statisticDTO.setTeamTwoGoals(2);
        List<FactorDTO> factorsTest = createFactorsTest();
        FactorDTO winningFactor = new FactorDTO(FactorName.lose, 2.1, 50L);

        when(eventDao.getEventById(eventTest.getId())).thenReturn(eventTest);
        when(eventDao.addStatistics(statisticDTO)).thenReturn(statisticDTO);
        when(eventDao.getEventFactors(eventTest.getId())).thenReturn(factorsTest);
        when(eventUtil.getWinningFactor(statisticDTO,factorsTest)).thenReturn(winningFactor);
        eventService.makeEventFinished(statisticDTO);
        verify(eventDao,times(1)).getEventById(statisticDTO.getEventId());
        verify(eventDao,times(1)).addStatistics(statisticDTO);
        verify(eventDao,times(1)).getEventFactors(eventTest.getId());
        verify(eventUtil,times(1)).getWinningFactor(statisticDTO,factorsTest);
        verify(eventDao,times(1)).addResultFactorId(eventTest);
    }

    @Test
    void getEventStatistic(){
        eventService.getEventStatistic(1L);
        verify(eventDao,times(1)).getEventStatistic(anyLong());
    }
}
