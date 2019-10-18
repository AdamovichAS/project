package com.github.adamovichas.project.web.servlet.event.add;

import com.github.adamovichas.project.service.data.impl.DataEventService;
import com.github.adamovichas.project.service.data.IdataEventService;
import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEvent extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddEvent.class);

    private IdataEventService data;

    @Override
    public void init() throws ServletException {
        data = DataEventService.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = (Event) req.getAttribute("event");
        boolean isExist = data.eventIsExist(event);
        if(!isExist){
            Long eventId = data.addEvent(event);
            EventView eventView = data.getEventById(eventId);
            log.info("Event saved {}",eventView);
            req.setAttribute("savedEvent",eventView);
        }else {
            String message = "Event is Exist";
            log.info(message);
            req.setAttribute("savedEvent",message);
        }
        req.getRequestDispatcher("/add_event.jsp").forward(req,resp);
    }
}
