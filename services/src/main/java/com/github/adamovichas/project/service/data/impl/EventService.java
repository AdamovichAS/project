package com.github.adamovichas.project.service.data.impl;


import com.github.adamovichas.project.dao.ILeagueDao;
import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.dao.IEventDao;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EventService implements IEventService {

    private static final int PAGE_SIZE = 5;

    private final IEventDao eventDao;
    private final ILeagueDao leagueDao;
    private final IEventUtil eventUtil;

    public EventService(IEventDao eventDao, ILeagueDao leagueDao, IEventUtil eventUtil) {
        this.eventDao = eventDao;
        this.leagueDao = leagueDao;
        this.eventUtil = eventUtil;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<LeagueDTO> getAllLeagues() {
        return leagueDao.getAllLeagues();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TeamDTO> getAllTeamsByLeague(Long idLeague) {
        return leagueDao.getAllTeamsByLeague(idLeague);
    }

    @Override
    public boolean eventIsExist(EventDTO eventDTO) {
        return eventDao.eventIsExist(eventDTO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long addEvent(EventDTO eventDTO) {
        return eventDao.addEvent(eventDTO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<EventView> getAllNotFinishedEvents() {
        return eventDao.getAllNotFinishedEvents();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EventDTO getEventById(Long id) {
        return eventDao.getEventById(id);
    }

    @Override
    @Transactional
    public EventView getEventViewById(Long id) {
        return eventDao.getEventViewById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long getEventMaxPages() {
        Long rowsEvent = eventDao.getCountEvents();
        Long maxPages = rowsEvent / PAGE_SIZE;
        if (rowsEvent % PAGE_SIZE > 0) {
            maxPages++;
        }
        return maxPages;
    }

    @Override
    @Transactional
    public Long getEventMaxPagesByResultFactorId(boolean isNull){
        Long rowsEvent = eventDao.getCountEventsByResultFactorId(isNull);
        Long maxPages = rowsEvent / PAGE_SIZE;
        if (rowsEvent % PAGE_SIZE > 0) {
            maxPages++;
        }
        return maxPages;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<EventView> getEventsOnCurrentPageByResultFactorId(int page, boolean isNull) {
        return eventDao.getEventsOnPageByResultFactorId(page, PAGE_SIZE, isNull);
    }

    @Override
    @Transactional
    public List<EventView> getEventsOnCurrentPage(int page){
        return eventDao.getAllEventsOnPage(page,PAGE_SIZE);
    }

    /**
     * Factor
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addFactors(List<FactorDTO> factorDTOS) {
        eventDao.addFactors(factorDTOS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<FactorDTO> getEventFactors(Long eventId) {
        return eventDao.getEventFactors(eventId);
    }

    /**
     *Statistic
     * @return
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FactorDTO makeEventFinished(EventStatisticDTO statisticDto) {
        EventDTO eventDTO = eventDao.getEventById(statisticDto.getEventId());
        statisticDto =  eventDao.addStatistics(statisticDto);
        List<FactorDTO> eventFactors = eventDao.getEventFactors(statisticDto.getEventId());
        FactorDTO winningFactor = eventUtil.getWinningFactor(statisticDto, eventFactors);
        eventDTO.setResultFactorId(winningFactor.getId());
        eventDao.addResultFactorId(eventDTO);
        return winningFactor;
    }



    @Override
    public EventStatisticDTO getEventStatistic(Long eventId) {
        return eventDao.getEventStatistic(eventId);
    }
}
