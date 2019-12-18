package com.github.adamovichas.project.web.controller.admin.event;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import com.github.adamovichas.project.web.request.EventRequest;
import com.github.adamovichas.project.web.validation.IEventValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(value = "/admin/event")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    private final IEventService eventService;
    private final IEventValidation eventVal;
    private final IEventUtil eventUtil;

    public EventController(IEventService eventService, IEventValidation eventVal, IEventUtil eventUtil) {
        this.eventService = eventService;
        this.eventVal = eventVal;
        this.eventUtil = eventUtil;
    }

    @GetMapping(value = "/add/chose_league")
    public ModelAndView choseLeagueGet(){
        List<LeagueDTO> allLeagueDTOS = eventService.getAllLeagues();
        ModelAndView modelAndView = new ModelAndView("add_event","allLeagues", allLeagueDTOS);
        return modelAndView;
    }

    @PostMapping(value = "/add/chose_league/add_info")
    public ModelAndView addEventInfo(Long leagueId){
        final ModelAndView modelAndView = new ModelAndView("add_event", "leagueId", leagueId);
        List<TeamDTO> allTeamsByLeague = eventService.getAllTeamsByLeague(leagueId);
        modelAndView.addObject("allTeams", allTeamsByLeague);
        return modelAndView;
    }

    @PostMapping(value = "/add/chose_league/add_info/create")
    public ModelAndView createEvent(EventRequest eventRequest){
        ModelAndView modelAndView = new ModelAndView("add_event");
        String errorMessage = eventVal.checkEventParam(eventRequest);
        if(nonNull(errorMessage)){
            modelAndView.addObject("error",errorMessage);
            modelAndView.addObject("leagueId",eventRequest.getLeagueId());
            return modelAndView;
        }
        EventDTO eventDto = eventRequest.getEventDto();
        List<FactorDTO> factors = eventRequest.getFactors();
        boolean isExist = eventService.eventIsExist(eventDto);
        if(!isExist){
            Long eventId = eventService.addEvent(eventDto);
            for (FactorDTO factor : factors) {
                factor.setEventId(eventId);
            }
            eventService.addFactors(factors);
            EventView eventView = eventService.getEventViewById(eventId);
            modelAndView.addObject("savedEvent",eventView);
            log.info("Event saved {} at {}",eventView, LocalDateTime.now());
            return modelAndView;
        }else {
            errorMessage = "EventDTO is Exist";
            log.info("Event {} is exist in data base, time {}", eventDto, LocalDateTime.now());
            modelAndView.addObject("error",errorMessage);
            return modelAndView;
        }
    }
}
