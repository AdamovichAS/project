package com.github.adamovichas.project.web.data;

import com.github.adamovichas.project.web.dto.EventView;
import com.github.adamovichas.project.web.dto.League;
import com.github.adamovichas.project.web.dto.Team;
import com.github.adamovichas.project.web.event.Event;

import java.util.List;

public interface IdataEventService {
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(Event event);
    Long addEvent(Event event);
    List<EventView> getAllNotFinishedEvents();
    EventView getEventById(Long id);
}
