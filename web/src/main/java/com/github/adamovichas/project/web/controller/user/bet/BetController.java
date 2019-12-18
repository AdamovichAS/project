package com.github.adamovichas.project.web.controller.user.bet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import com.github.adamovichas.project.web.controller.MainPageController;
import com.github.adamovichas.project.web.request.BetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(value = "/user/bet")
public class BetController {

    private static final Logger log = LoggerFactory.getLogger(BetController.class);

    private final IEventService eventService;

    private final IEventUtil eventUtil;

    private final IBetService betService;

    private final ICashAccountService cashAccountService;

    public BetController(IEventService eventService, IEventUtil eventUtil, IBetService betService, ICashAccountService cashAccountService) {
        this.eventService = eventService;
        this.eventUtil = eventUtil;
        this.betService = betService;
        this.cashAccountService = cashAccountService;
    }
    @GetMapping(value = "/new")
    public ModelAndView newBet(HttpServletRequest req,ModelAndView modelAndView){
        modelAndView.setViewName("bet");
        List<EventView> events = eventService.getAllNotFinishedEvents();
        modelAndView.addObject("events", events);
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        CashAccountDTO cashAccountDTOById = cashAccountService.getAccountByLogin(authUser.getLogin());
        modelAndView.addObject("user_money", cashAccountDTOById);
        return modelAndView;
    }
    @PostMapping(value = "/new")
    public ModelAndView newBetPost(HttpServletRequest req, BetRequest betRequest){
        final List<FactorDTO> eventFactors = eventService.getEventFactors(betRequest.getEventId());
        final FactorDTO bettingFactor = eventUtil.getFactorByName(eventFactors, betRequest.getFactorName());
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        BetDTO betDTO = new BetDTO(authUser.getLogin(), bettingFactor.getId(), betRequest.getMoneyForBet());
        Long idBet = betService.addBet(betDTO);
        BetView view = betService.getViewById(idBet);
        log.info("Bet saved {} at {}",view, LocalDateTime.now());
        final ModelAndView modelAndView = new ModelAndView("bet", "betView", view);
//        req.setAttribute("betView", view);
        newBet(req,modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/cancel_bet")
    public String cancelBet(HttpServletRequest req){
        String[] betIds = req.getParameterValues("betId");
        if(nonNull(betIds)) {
            List<Long> betsId = new ArrayList<>();
            for (String id : betIds) {
                betsId.add(Long.valueOf(id));
            }
            betService.cancelBetById(betsId);
        }
        return "redirect:/user/";
    }
}
