package by.itacademy.jd2.servlets.event;

import by.itacademy.jd2.DAO.DAOEvent;
import by.itacademy.jd2.DAO.IDAOEvent;
import by.itacademy.jd2.dto.League;
import by.itacademy.jd2.dto.Team;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChoseLeague extends HttpServlet {

    private IDAOEvent dataEvent;
    @Override
    public void init() throws ServletException {
        dataEvent = DAOEvent.DAO_EVENT;
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
        }
        req.removeAttribute("allLeague");
        req.setAttribute("leagueId",Long.valueOf(leagueId));
        req.getRequestDispatcher("/new_event/chose_league/chose_event_info").forward(req,resp);
    }
}
