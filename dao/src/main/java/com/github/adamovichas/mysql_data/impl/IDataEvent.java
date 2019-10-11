package com.github.adamovichas.mysql_data.impl;

import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.dto.League;
import com.github.adamovichas.dto.Team;
import com.github.adamovichas.event.Event;

import java.util.List;

public interface IDataEvent {
    Long addEvent(Event event);
    void updateEventInfo(Event event);
    void deleteEvent(Event event);
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    Event eventIsExist(Event event);
    List<EventView> getAllNotFinisedEvents();
    EventView getSavedEventById(Long id);
}
