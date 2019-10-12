package com.github.adamovichas.mysql_data;

import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.dto.League;
import com.github.adamovichas.dto.Team;
import com.github.adamovichas.event.Event;

import java.util.List;

public interface IDataEvent {
    Long addEvent(Event event);
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    Event eventIsExist(Event event);
    List<EventView> getAllNotFinishedEvents();
    EventView getSavedEventById(Long id);
}
