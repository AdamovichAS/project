package com.github.adamovichas.data.impl;


import com.github.adamovichas.data.IdataEventService;
import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.dto.League;
import com.github.adamovichas.dto.Team;
import com.github.adamovichas.event.Event;
import com.github.adamovichas.mysql_data.impl.DataEvent;
import com.github.adamovichas.mysql_data.IDataEvent;

import java.util.List;

import static java.util.Objects.nonNull;

public enum DataEventService implements IdataEventService {
    DATA_EVENT_SERVICE;

    private IDataEvent dataEvent;

    DataEventService() {
        dataEvent = DataEvent.DATA_EVENT;
    }

    @Override
    public List<League> getAllLeagues() {
        return dataEvent.getAllLeagues();
    }

    @Override
    public List<Team> getAllTeamsByLeague(Long idLeague) {
        return dataEvent.getAllTeamsByLeague(idLeague);
    }

    @Override
    public boolean eventIsExist(Event event) {
        Event dataBaseEvent = dataEvent.eventIsExist(event);
        return nonNull(dataBaseEvent);
    }

    @Override
    public Long addEvent(Event event) {
        return dataEvent.addEvent(event);
    }

    @Override
    public List<EventView> getAllNotFinishedEvents() {
        return dataEvent.getAllNotFinishedEvents();
    }

    @Override
    public EventView getEventById(Long id) {
        return dataEvent.getSavedEventById(id);
    }
}
