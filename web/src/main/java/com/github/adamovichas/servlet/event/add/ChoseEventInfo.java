package com.github.adamovichas.servlet.event.add;

import com.github.adamovichas.data.impl.DataEventService;
import com.github.adamovichas.data.IdataEventService;
import com.github.adamovichas.dto.Team;
import com.github.adamovichas.event.Event;
import com.github.adamovichas.event.Factor;
import com.github.adamovichas.util.EventUtil;
import com.github.adamovichas.util.IEventUtil;
import com.github.adamovichas.validation.EventValidation;
import com.github.adamovichas.validation.IEventValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static java.util.Objects.nonNull;

public class ChoseEventInfo extends HttpServlet {

    private IdataEventService dataEvent;
    private IEventValidation eventVal;
    private IEventUtil eventUtil;

    @Override
    public void init() throws ServletException {
        dataEvent = DataEventService.getInstance();
        eventVal = EventValidation.EVENT_VALIDATION;
        eventUtil = EventUtil.EVENT_UTIL;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long leagueId = (Long) req.getAttribute("leagueId");
        List<Team> allTeamsByLeague = dataEvent.getAllTeamsByLeague(leagueId);
        req.setAttribute("allTeams", allTeamsByLeague);
        req.getRequestDispatcher("/add_event.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamOneId = req.getParameter("teamOneId");
        if (teamOneId == null) {
            doGet(req, resp);
        }
        Long teamTwoId = Long.valueOf(req.getParameter("teamTwoId"));
        Timestamp start = eventVal.formatDate(req.getParameter("start"));
        Timestamp end = eventVal.formatDate(req.getParameter("end"));
        Event event = new Event(Long.valueOf(teamOneId),teamTwoId,start,end);
        double win = Double.valueOf(req.getParameter("win"));
        double lose = Double.valueOf(req.getParameter("lose"));
        double draw = Double.valueOf(req.getParameter("draw"));
        List<Factor> factors = eventUtil.createFactors(win, lose, draw);
        event.setFactors(factors);
        String errorMessage = eventVal.checkEventParam(event);
        if(nonNull(errorMessage)){
            req.setAttribute("error",errorMessage);
            req.getRequestDispatcher("/new_event/chose_league/").forward(req,resp);
        }

        req.removeAttribute("allTeams");
        req.setAttribute("event",event);
        req.getRequestDispatcher("/new_event/add_event/").forward(req,resp);

    }
}
