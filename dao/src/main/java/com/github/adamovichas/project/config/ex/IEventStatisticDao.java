package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.dto.EventStatisticDto;

public interface IEventStatisticDao {

    boolean isExist(Long id);
    Long addStatistic(EventStatisticDto dto);
}
