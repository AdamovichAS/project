package com.github.adamovichas.servlet.event.add;

import com.github.adamovichas.DAO.DAOEvent;
import com.github.adamovichas.DAO.impl.IDAOEvent;
import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.event.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEvent extends HttpServlet {

    private IDAOEvent data;

    @Override
    public void init() throws ServletException {
        data = DAOEvent.DAO_EVENT;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = (Event) req.getAttribute("event");
        boolean isExist = data.eventIsExist(event);
        if(!isExist){
            Long eventId = data.addEvent(event);
            EventView eventView = data.getEventById(eventId);
            req.setAttribute("savedEvent",eventView);
        }else {
            req.setAttribute("savedEvent","Event is Exist");
        }
        req.getRequestDispatcher("/add_event.jsp").forward(req,resp);
    }
}
