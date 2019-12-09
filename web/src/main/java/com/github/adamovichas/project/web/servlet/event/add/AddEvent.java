package com.github.adamovichas.project.web.servlet.event.add;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.service.data.impl.EventService;
import com.github.adamovichas.project.service.data.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEvent extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddEvent.class);

    private IEventService data;

    @Override
    public void init() throws ServletException {
        data = EventService.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.github.adamovichas.project.model.dto.EventDTO eventDTO = (com.github.adamovichas.project.model.dto.EventDTO) req.getAttribute("factor");
        boolean isExist = data.eventIsExist(eventDTO);
        if(!isExist){
            Long eventId = data.addEvent(eventDTO);
            EventDTO eventView = data.getEventById(eventId);
            log.info("EventDTO saved {}",eventView);
            req.setAttribute("savedEvent",eventView);
        }else {
            String message = "EventDTO is Exist";
            log.info(message);
            req.setAttribute("savedEvent",message);
        }
        req.getRequestDispatcher("/add_event.jsp").forward(req,resp);
    }
}
