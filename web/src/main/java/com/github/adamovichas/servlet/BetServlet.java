package com.github.adamovichas.servlet;

import com.github.adamovichas.data.impl.DataBetService;
import com.github.adamovichas.data.impl.DataEventService;
import com.github.adamovichas.data.IdataBetService;
import com.github.adamovichas.data.IdataEventService;
import com.github.adamovichas.dto.Bet;
import com.github.adamovichas.dto.BetView;
import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.event.Factor;
import com.github.adamovichas.dto.AuthUser;
import com.github.adamovichas.dto.Money;
import com.github.adamovichas.util.EventUtil;
import com.github.adamovichas.util.IEventUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BetServlet extends HttpServlet {

    private IdataEventService dataEvent;
    private IEventUtil util;
    private IdataBetService dataBet;

    private static final Logger log = LoggerFactory.getLogger(BetServlet.class);

    @Override
    public void init() throws ServletException {
        dataEvent = DataEventService.DATA_EVENT_SERVICE;
        util = EventUtil.EVENT_UTIL;
        dataBet = DataBetService.DATA_BET_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EventView> events = dataEvent.getAllNotFinishedEvents();
        req.setAttribute("events", events);
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        Money moneyById = dataBet.getMoneyByLogin(authUser.getLogin());
        req.setAttribute("user_money", moneyById);
        req.getRequestDispatcher("/bet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long eventId = Long.valueOf(req.getParameter("event"));
        int moneyForBet = Integer.parseInt(req.getParameter("money_for_bet"));
        EventView eventView = dataEvent.getEventById(eventId);
        String factorName = req.getParameter("factor");
        Factor factor = util.getFactorByName(eventView, factorName);
        Long idFactor = factor.getId();
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        Bet bet = new Bet(authUser.getLogin(), idFactor, moneyForBet);
        Long idBet = dataBet.addBet(bet);
        BetView view = dataBet.getViewById(idBet);
        log.info("Bet saved {}",view);
        req.setAttribute("bet", view);
        doGet(req,resp);

    }
}