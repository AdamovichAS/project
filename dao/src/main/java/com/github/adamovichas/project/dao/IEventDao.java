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
    List<EventView> getAllNotFinishedEvents();
    EventDTO getEventById(Long id);
    EventView getEventViewById(Long id);
    Long getCountEvents();
    Long getCountEventsByResultFactorId(boolean isNull);
    List<EventView> getEventsOnPageByResultFactorId(int page, int pageSize, boolean isNull);
    List<EventView> getAllEventsOnPage(int page, int pageSize);
    void addResultFactorId(EventDTO eventDTO);

    /**
     * Factor
     */

    void addFactors(List<FactorDTO> factorDTOS);
    List<FactorDTO> getEventFactors(Long EventId);

    /**
     *Statistic
     * @return
     */
    EventStatisticDTO addStatistics(EventStatisticDTO statisticDto);

//    boolean addStatistics(EventStatisticDTO statisticDto);

    EventStatisticDTO getEventStatistic(Long eventId);


}
