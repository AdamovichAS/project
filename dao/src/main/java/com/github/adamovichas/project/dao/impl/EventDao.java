package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IEventDao;
import com.github.adamovichas.project.dao.repository.EventRepository;
import com.github.adamovichas.project.dao.repository.EventStatisticRepository;
import com.github.adamovichas.project.dao.repository.FactorRepository;
import com.github.adamovichas.project.dao.repository.LeagueRepository;
import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.entity.EventStatisticEntity;
import com.github.adamovichas.project.entity.FactorEntity;
import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class EventDao implements IEventDao {


    private static final Logger log = LoggerFactory.getLogger(EventDao.class);

    private final EventRepository eventRepository;
    private final EventStatisticRepository statisticRepository;
    private final LeagueRepository leagueRepository;
    private final FactorRepository factorRepository;

    public EventDao(EventRepository eventRepository, FactorRepository factorRepository, EventStatisticRepository statisticRepository, LeagueRepository leagueRepository) {
        this.eventRepository = eventRepository;
        this.factorRepository = factorRepository;
        this.statisticRepository = statisticRepository;
        this.leagueRepository = leagueRepository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Long addEvent(EventDTO event) {
        EventEntity eventEntity = EntityDtoViewConverter.getEntity(event);
        LeagueEntity league = leagueRepository.getOne(event.getLeagueId());
        eventEntity.setLeague(league);
        league.getEvents().add(eventEntity);
        eventRepository.save(eventEntity);
        eventRepository.flush();
        return eventEntity.getId();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean eventIsExist(EventDTO event) {
        return eventRepository.existsByTeamOneIdAndTeamTwoIdAndStartTime(event.getTeamOne(),event.getTeamTwo(),event.getStartTime());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EventView> getAllNotFinishedEvents() {
        List<EventEntity> eventEntities = eventRepository.getAllByResultFactorIdIsNull();
        return getViews(eventEntities);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addResultFactorId(EventDTO eventDTO) {
        EventEntity eventEntity = eventRepository.getOne(eventDTO.getId());
        eventEntity.setResultFactorId(eventDTO.getResultFactorId());
        eventRepository.flush();
    }

    private List<EventView> getViews(List<EventEntity> entities){
        List<EventView>views = new ArrayList<>();
        for (EventEntity entity : entities) {
            views.add(EntityDtoViewConverter.getView(entity));
        }
        return views;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public EventDTO getEventById(Long id) {
         EventEntity eventEntity = eventRepository.getOne(id);
         return EntityDtoViewConverter.getDTO(eventEntity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Long getCountEvents() {
        return eventRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Long getCountEventsByResultFactorId(boolean isNull){
        Long eventsCount;
        if(isNull){
            eventsCount = eventRepository.getCountEventsByResultFactorIdIsNull();
        }else {
            eventsCount = eventRepository.count() - eventRepository.getCountEventsByResultFactorIdIsNull();
        }
        return eventsCount;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EventView> getEventsOnPageByResultFactorId(int page, int pageSize, boolean isNull) {
        List<EventEntity> eventEntities;
        if(isNull){
            eventEntities = eventRepository.getAllByResultFactorIdIsNull(PageRequest.of(page - 1, pageSize, Sort.by("startTime")));
        }else {
            eventEntities = eventRepository.getAllByResultFactorIdIsNotNull(PageRequest.of(page - 1, pageSize, Sort.by("startTime")));
        }
        return getViews(eventEntities);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<EventView> getAllEventsOnPage(int page, int pageSize){
        List<EventEntity> eventEntities = eventRepository.findAll(PageRequest.of(page - 1, pageSize, Sort.by("startTime"))).toList();
        return getViews(eventEntities);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public EventView getEventViewById(Long id) {
        EventEntity eventEntity = eventRepository.getOne(id);
        return EntityDtoViewConverter.getView(eventEntity);
    }

    /**
     *Factors
     */

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addFactors(List<FactorDTO> factorDTOS) {
        EventEntity eventEntity = eventRepository.getOne(factorDTOS.get(0).getEventId());
        List<FactorEntity>factorEntities = new ArrayList<>();
        for (FactorDTO dto : factorDTOS) {
            FactorEntity factorEntity = EntityDtoViewConverter.getEntity(dto);
            factorEntity.setEvent(eventEntity);
            factorEntities.add(factorEntity);
        }
        eventEntity.setFactors(factorEntities);
        factorRepository.saveAll(factorEntities);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<FactorDTO> getEventFactors(Long eventId) {
        List<FactorDTO>factorDTOS = new ArrayList<>();
        List<FactorEntity> factorEntities = factorRepository.getAllByEventId(eventId);
        for (FactorEntity entity : factorEntities) {
            factorDTOS.add(EntityDtoViewConverter.getDTO(entity));
        }
        return factorDTOS;
    }

    /**
     * EventStatistic
     * @return
     */

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public EventStatisticDTO addStatistics(EventStatisticDTO statisticDto) {
        EventStatisticEntity statisticEntity = EntityDtoViewConverter.getEntity(statisticDto);
        EventEntity eventEntity = eventRepository.getOne(statisticDto.getEventId());
        statisticEntity.setEvent(eventEntity);
        eventEntity.setStatistic(statisticEntity);
 //       statisticRepository.save(statisticEntity);
        statisticRepository.flush();
        return EntityDtoViewConverter.getDTO(statisticEntity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public EventStatisticDTO getEventStatistic(Long eventId) {
        EventStatisticEntity statisticEntity = statisticRepository.getOne(eventId);
        return EntityDtoViewConverter.getDTO(statisticEntity);
    }
}