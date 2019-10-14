package com.github.adamovichas.project.project.dao;

import com.github.adamovichas.project.web.dto.EventView;
import com.github.adamovichas.project.web.dto.League;
import com.github.adamovichas.project.web.dto.Team;
import com.github.adamovichas.project.web.event.Event;

import java.util.List;

public interface IDataEvent {
    Long addEvent(Event event);
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    Event eventIsExist(Event event);
    List<EventView> getAllNotFinishedEvents();
    EventView getSavedEventById(Long id);
}
