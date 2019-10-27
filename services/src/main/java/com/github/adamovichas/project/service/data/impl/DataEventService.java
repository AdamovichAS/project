package com.github.adamovichas.project.service.data.impl;


import com.github.adamovichas.project.service.data.IdataEventService;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.dao.impl.DataEvent;
import com.github.adamovichas.project.IDataEvent;

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
    public List<LeagueDTO> getAllLeagues() {
        return dataEvent.getAllLeagues();
    }

    @Override
    public List<TeamDTO> getAllTeamsByLeague(Long idLeague) {
        return dataEvent.getAllTeamsByLeague(idLeague);
    }

    @Override
    public boolean eventIsExist(EventDTO eventDTO) {
        return dataEvent.eventIsExist(eventDTO);
    }

    @Override
    public Long addEvent(EventDTO eventDTO) {
        return dataEvent.addEvent(eventDTO);
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
