package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.impl.BetService;
import com.github.adamovichas.project.service.data.impl.EventService;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.service.data.impl.CashAccountService;
import com.github.adamovichas.project.service.util.event.EventUtil;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BetServlet extends HttpServlet {

    private IEventService dataEvent;
    private IEventUtil util;
    private IBetService dataBet;
    private ICashAccountService dataMoney;

    private static final Logger log = LoggerFactory.getLogger(BetServlet.class);

    @Override
    public void init() throws ServletException {
        dataEvent = EventService.getInstance();
        util = EventUtil.EVENT_UTIL;
        dataBet = BetService.getInstance();
        dataMoney = CashAccountService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EventDTO> events = dataEvent.getAllNotFinishedEvents();
        req.setAttribute("events", events);
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        CashAccountDTO cashAccountDTOById = dataMoney.getAccountByLogin(authUser.getLogin());
        req.setAttribute("user_money", cashAccountDTOById);
        req.getRequestDispatcher("/bet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long eventId = Long.valueOf(req.getParameter("eventId"));
        Double moneyForBet = Double.parseDouble(req.getParameter("money_for_bet"));
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
