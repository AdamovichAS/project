package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IEventStatisticDao;
import com.github.adamovichas.project.dao.repository.EventStatisticRepository;
import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.entity.EventStatisticEntity;
import com.github.adamovichas.project.model.dto.EventStatisticDto;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.hibernate.Session;

import javax.persistence.PersistenceContext;

public class EventStatisticDao implements IEventStatisticDao {

    private final EventStatisticRepository repository;

    @PersistenceContext
    private Session session;

    public EventStatisticDao(EventStatisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isExist(Long eventId) {
        return repository.existsByEventId(eventId);
    }

    @Override
    public Long addStatistic(EventStatisticDto dto) {
        EventStatisticEntity statisticEntity = EntityDtoViewConverter.getEntity(dto);
        EventEntity eventEntity = session.find(EventEntity.class, dto.getEventId());
        eventEntity.setStatistic(statisticEntity);
        statisticEntity.setEvent(eventEntity);
        return statisticEntity.getEventId();
    }
}
