package com.github.adamovichas.DAO.impl;

import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.dto.League;
import com.github.adamovichas.dto.Team;
import com.github.adamovichas.event.Event;

import java.util.List;

public interface IDAOEvent {
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(Event event);
    Long addEvent(Event event);
    List<EventView> getAllNotFinishedEvents();
    EventView getEventById(Long id);
}
