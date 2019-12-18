package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "BetServlet", urlPatterns = {"/bet"})
//public class BetServlet extends HttpServlet {
//
//    @Autowired
//    private IEventService eventService;
//    @Autowired
//    private IEventUtil eventUtil;
//    @Autowired
//    private IBetService betService;
//    @Autowired
//    private ICashAccountService cashAccountService;
//
//    private static final Logger log = LoggerFactory.getLogger(BetServlet.class);
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<EventView> events = eventService.getAllNotFinishedEvents();
//        req.setAttribute("events", events);
//        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
//        CashAccountDTO cashAccountDTOById = cashAccountService.getAccountByLogin(authUser.getLogin());
//        req.setAttribute("user_money", cashAccountDTOById);
//        req.getRequestDispatcher("/bet.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long eventId = Long.valueOf(req.getParameter("eventId"));
//        Double moneyForBet = Double.parseDouble(req.getParameter("money_for_bet"));
//        EventDTO eventDTO = eventService.getEventById(eventId);
//        List<FactorDTO> eventFactors = eventService.getEventFactors(eventId);
//        String factorName = req.getParameter("factorName");
//        FactorDTO factorDTO = eventUtil.getFactorByName(eventFactors, factorName);
//        Long idFactor = factorDTO.getId();
//        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
//        BetDTO betDTO = new BetDTO(authUser.getLogin(), idFactor, moneyForBet);
//        Long idBet = betService.addBet(betDTO);
//        BetView view = betService.getViewById(idBet);
//        log.info("BetDTO saved {}",view);
//        req.setAttribute("bet", view);
//        doGet(req,resp);
//
//    }
//}
