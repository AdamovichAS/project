package com.github.adamovichas.project.service.data.impl;


import com.github.adamovichas.project.service.data.IdataEventService;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.IDataEvent;

import java.util.List;

public class DataEventService implements IdataEventService {

    private static final int PAGE_SIZE = 5;

    private final IDataEvent dataEvent;

    public DataEventService(IDataEvent dataEvent) {
        this.dataEvent = dataEvent;
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
    public boolean eventIsExist(com.github.adamovichas.project.model.dto.EventDTO eventDTO) {
        return dataEvent.eventIsExist(eventDTO);
    }

    @Override
    public Long addEvent(com.github.adamovichas.project.model.dto.EventDTO eventDTO) {
        return dataEvent.addEvent(eventDTO);
    }

    @Override
    public List<EventDTO> getAllNotFinishedEvents() {
        return dataEvent.getAllNotFinishedEvents();
    }

    @Override
    public EventDTO getEventById(Long id) {
        return dataEvent.getSavedEventById(id);
    }

    @Override
    public Long getEventMaxPages() {
        Long rowsEvent = dataEvent.getCountEvents();
        Long maxPages = rowsEvent / PAGE_SIZE;
        if (rowsEvent % PAGE_SIZE > 0) {
            maxPages++;
        }
        return maxPages;
    }

    @Override
    public List<EventDTO> getEventsOnCurrentPage(int page) {
        return dataEvent.getEventsOnPage(page, PAGE_SIZE);
    }
}
