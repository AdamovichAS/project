package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.entity.League;
import com.github.adamovichas.project.entity.Team;
import com.github.adamovichas.project.entity.Event;

import java.util.List;

public interface IdataEventService {
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(Event event);
    Long addEvent(Event event);
    List<EventView> getAllNotFinishedEvents();
    EventView getEventById(Long id);
}
