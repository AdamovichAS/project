package com.github.adamovichas.project;

import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.model.dto.EventDTO;

import java.util.List;

public interface IDataEvent {
    Long addEvent(EventDTO eventDTO);
    List<LeagueDTO> getAllLeagues();
    List<TeamDTO> getAllTeamsByLeague(Long idLeague);
    EventDTO eventIsExist(EventDTO eventDTO);
    List<EventView> getAllNotFinishedEvents();
    EventView getSavedEventById(Long id);
}
