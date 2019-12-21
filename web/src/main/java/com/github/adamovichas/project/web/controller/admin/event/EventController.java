package com.github.adamovichas.project.web.controller.admin.event;

import com.github.adamovichas.project.model.dto.*;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import com.github.adamovichas.project.web.request.EventRequest;
import com.github.adamovichas.project.web.validation.IEventValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final ICashAccountService cashAccountService;

    public EventController(IEventService eventService, IEventValidation eventVal, ICashAccountService cashAccountService) {
        this.eventService = eventService;
        this.eventVal = eventVal;
        this.cashAccountService = cashAccountService;
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

    @GetMapping(value = "/get_not_finished_events")
    public ModelAndView getNotFinishedEventsPagination(@RequestParam(value = "action") String action,
                                                       @RequestParam(value = "currentPage",required = false) Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("admin_page", "action", action);
        AppCashAccountDTO appCashAccount = cashAccountService.getAppCashAccount();
        modelAndView.addObject("appCash",appCashAccount);
        if (currentPage == null) {
            currentPage = 1;
        }
        modelAndView.addObject("currentPage", currentPage);
        List<EventView> events = eventService.getEventsOnCurrentPageByResultFactorId(currentPage,true);
        if(!events.isEmpty()) {
            modelAndView.addObject("eventsList", events);
        }
        Long maxPages = eventService.getEventMaxPagesByResultFactorId(true);
        modelAndView.addObject("maxPages", maxPages);
        return modelAndView;
    }

    @GetMapping(value = "/get_not_finished_events/add_statistic")
    public ModelAndView getEventStatistic(@RequestParam(value = "action") String action,
                                          @RequestParam(value = "currentPage") Integer currentPage,
                                          @RequestParam(value = "eventId") Long eventId){
        ModelAndView modelAndView = getNotFinishedEventsPagination(action, currentPage);
        modelAndView.addObject("eventId",eventId);
        return modelAndView;
    }

    @PostMapping(value = "/get_not_finished_events/add_statistic")
    public ModelAndView addStatistic(EventStatisticDTO statistic){
        FactorDTO winningFactor = eventService.makeEventFinished(statistic);
        List<FactorDTO> eventFactors = eventService.getEventFactors(statistic.getEventId());
        cashAccountService.eventCashAccountsCalculation(eventFactors,winningFactor);
        ModelAndView modelAndView = getNotFinishedEventsPagination("finish", 1);
        return modelAndView;
    }
}
