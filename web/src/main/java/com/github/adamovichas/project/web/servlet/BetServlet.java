package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.MoneyDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.service.data.IDataMoneyService;
import com.github.adamovichas.project.service.data.impl.DataBetService;
import com.github.adamovichas.project.service.data.impl.DataEventService;
import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.service.data.IdataEventService;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.service.data.impl.DataMoneyService;
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
    private IDataMoneyService dataMoney;

    private static final Logger log = LoggerFactory.getLogger(BetServlet.class);

    @Override
    public void init() throws ServletException {
        dataEvent = DataEventService.getInstance();
        util = EventUtil.EVENT_UTIL;
        dataBet = DataBetService.getInstance();
        dataMoney = DataMoneyService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EventDTO> events = dataEvent.getAllNotFinishedEvents();
        req.setAttribute("events", events);
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        MoneyDTO moneyDTOById = dataMoney.getMoneyByLogin(authUser.getLogin());
        req.setAttribute("user_money", moneyDTOById);
        req.getRequestDispatcher("/bet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long eventId = Long.valueOf(req.getParameter("eventId"));
        int moneyForBet = Integer.parseInt(req.getParameter("money_for_bet"));
        EventDTO eventDTO = dataEvent.getEventById(eventId);
        String factorName = req.getParameter("factorName");
        FactorDTO factorDTO = util.getFactorByName(eventDTO, factorName);
        Long idFactor = factorDTO.getId();
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        BetDTO betDTO = new BetDTO(authUser.getLogin(), idFactor, moneyForBet);
        Long idBet = dataBet.addBet(betDTO);
        BetView view = dataBet.getViewById(idBet);
        log.info("BetDTO saved {}",view);
        req.setAttribute("betDTO", view);
        doGet(req,resp);

    }
}
