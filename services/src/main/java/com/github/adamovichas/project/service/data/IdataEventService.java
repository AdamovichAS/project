package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;

import java.util.List;

public interface IdataEventService {
    List<LeagueDTO> getAllLeagues();
    List<TeamDTO> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(com.github.adamovichas.project.model.dto.EventDTO eventDTO);
    Long addEvent(com.github.adamovichas.project.model.dto.EventDTO eventDTO);
    List<EventDTO> getAllNotFinishedEvents();
    EventDTO getEventById(Long id);
}
