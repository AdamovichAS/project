package com.github.adamovichas.project.project.dao;

import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.dto.League;
import com.github.adamovichas.project.model.dto.Team;
import com.github.adamovichas.project.model.event.Event;

import java.util.List;

public interface IDataEvent {
    Long addEvent(Event event);
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    Event eventIsExist(Event event);
    List<EventView> getAllNotFinishedEvents();
    EventView getSavedEventById(Long id);
}
