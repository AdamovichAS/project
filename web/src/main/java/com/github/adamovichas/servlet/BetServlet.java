package com.github.adamovichas.servlet;

import com.github.adamovichas.DAO.DAOBet;
import com.github.adamovichas.DAO.DAOEvent;
import com.github.adamovichas.DAO.impl.IDAOBet;
import com.github.adamovichas.DAO.impl.IDAOEvent;
import com.github.adamovichas.dto.Bet;
import com.github.adamovichas.dto.BetView;
import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.event.Factor;
import com.github.adamovichas.dto.AuthUser;
import com.github.adamovichas.dto.Money;
import com.github.adamovichas.util.EventUtil;
import com.github.adamovichas.util.IEventUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BetServlet extends HttpServlet {

    private IDAOEvent dataEvent;
    private IEventUtil util;
    private IDAOBet dataBet;

    @Override
    public void init() throws ServletException {
        dataEvent = DAOEvent.DAO_EVENT;
        util = EventUtil.EVENT_UTIL;
        dataBet = DAOBet.DAO_BET;
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
        req.setAttribute("bet", view);
        doGet(req,resp);

    }
}
