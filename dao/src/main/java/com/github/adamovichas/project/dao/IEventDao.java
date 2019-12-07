package com.github.adamovichas.project.dao;


import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.EventView;

import java.util.List;

public interface IEventDao {
    /**
     * Event
     */
    Long addEvent(EventDTO eventDTO);
    boolean eventIsExist(EventDTO eventDTO);
    List<EventDTO> getAllNotFinishedEvents();
    EventDTO getEventById(Long id);
    EventView getEventViewById(Long id);
    Long getCountEvents();
    List<EventDTO>getEventsOnPage(int page, int pageSize);

    /**
     * Factor
     */

    void addFactors(List<FactorDTO> factorDTOS);
    List<FactorDTO> getEventFactors(Long EventId);

    /**
     *Statistic
     */
    boolean addStatistics(EventStatisticDTO statisticDto, Long eventId);

//    boolean addStatistics(EventStatisticDTO statisticDto);

    EventStatisticDTO getEventStatistic(Long eventId);
}
