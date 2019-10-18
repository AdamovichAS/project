package com.github.adamovichas.project.web.servlet.event.add;

import com.github.adamovichas.project.service.data.impl.DataEventService;
import com.github.adamovichas.project.service.data.IdataEventService;
import com.github.adamovichas.project.model.dto.League;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChoseLeague extends HttpServlet {

    private IdataEventService dataEvent;
    @Override
    public void init(){
        dataEvent = DataEventService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<League> allLeagues = dataEvent.getAllLeagues();
        req.setAttribute("allLeagues",allLeagues);
        req.getRequestDispatcher("/add_event.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  leagueId = req.getParameter("league");
        if(leagueId == null){
            doGet(req,resp);
            return;
        }
        req.removeAttribute("allLeague");
        req.setAttribute("leagueId",Long.valueOf(leagueId));
        req.getRequestDispatcher("/new_event/chose_league/chose_event_info").forward(req,resp);
    }
}
