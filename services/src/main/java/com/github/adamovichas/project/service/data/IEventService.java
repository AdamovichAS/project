package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.EventView;

import java.util.List;

public interface IEventService {
    List<LeagueDTO> getAllLeagues();
    List<TeamDTO> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(EventDTO eventDTO);
    Long addEvent(EventDTO eventDTO);
    List<EventView> getAllNotFinishedEvents();
    EventDTO getEventById(Long id);
    Long getEventMaxPages();
    List<EventView> getEventsOnCurrentPage(int page);

    /**
     * Factor
     */

    void addFactors(List<FactorDTO> factorDTOS);
    List<FactorDTO> getEventFactors(Long EventId);

    /**
     *Statistic
     * @return
     */
    FactorDTO makeEventFinished(EventStatisticDTO statisticDto, Long eventId);

//    boolean makeEventFinished(EventStatisticDTO statisticDto);

    EventStatisticDTO getEventStatistic(Long eventId);
}
