package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.service.data.impl.DataBetService;
import com.github.adamovichas.project.service.data.impl.DataEventService;
import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.service.data.IdataEventService;
import com.github.adamovichas.project.entity.Bet;
import com.github.adamovichas.project.model.dto.BetView;
import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.event.Factor;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.entity.Money;
import com.github.adamovichas.project.service.util.EventUtil;
import com.github.adamovichas.project.service.util.IEventUtil;
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
    private IDataBetService dataBet;

    private static final Logger log = LoggerFactory.getLogger(BetServlet.class);

    @Override
    public void init() throws ServletException {
        dataEvent = DataEventService.getInstance();
        util = EventUtil.EVENT_UTIL;
        dataBet = DataBetService.getInstance();
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
