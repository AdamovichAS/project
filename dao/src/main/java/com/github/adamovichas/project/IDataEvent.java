package com.github.adamovichas.project;


import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;

import java.util.List;

public interface IDataEvent {
    Long addEvent(com.github.adamovichas.project.model.dto.EventDTO eventDTO);
    List<LeagueDTO> getAllLeagues();
    List<TeamDTO> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(com.github.adamovichas.project.model.dto.EventDTO eventDTO);
    List<EventDTO> getAllNotFinishedEvents();
    EventDTO getSavedEventById(Long id);
}
