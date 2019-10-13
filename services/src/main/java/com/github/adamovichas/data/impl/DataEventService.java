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

public class DataEventService implements IdataEventService {


    private IDataEvent dataEvent = DataEvent.DATA_EVENT;

    private static volatile IdataEventService instance;

    public static IdataEventService getInstance() {
        IdataEventService localInstance = instance;
        if (localInstance == null) {
            synchronized (IdataEventService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataEventService();
                }
            }
        }
        return localInstance;
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
