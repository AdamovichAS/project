package com.github.adamovichas.DAO;


import com.github.adamovichas.DAO.impl.IDAOEvent;
import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.dto.League;
import com.github.adamovichas.dto.Team;
import com.github.adamovichas.event.Event;
import com.github.adamovichas.mysql_data.DataEvent;
import com.github.adamovichas.mysql_data.impl.IDataEvent;

import java.util.List;

import static java.util.Objects.nonNull;

public enum  DAOEvent implements IDAOEvent {
    DAO_EVENT;

    private IDataEvent dataEvent;

    DAOEvent() {
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
        return dataEvent.getAllNotFinisedEvents();
    }

    @Override
    public EventView getEventById(Long id) {
        return dataEvent.getSavedEventById(id);
    }
}
