package com.github.adamovichas.project.dao;


import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDto;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;

import java.util.List;

public interface IEventDao {
    Long addEvent(com.github.adamovichas.project.model.dto.EventDTO eventDTO);
    List<LeagueDTO> getAllLeagues();
    List<TeamDTO> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(com.github.adamovichas.project.model.dto.EventDTO eventDTO);
    List<EventDTO> getAllNotFinishedEvents();
    EventDTO getEventById(Long id);
    Long getCountEvents();
    List<EventDTO>getEventsOnPage(int page, int pageSize);
    boolean addStatistics(EventStatisticDto statisticDto, Long eventId);
}
