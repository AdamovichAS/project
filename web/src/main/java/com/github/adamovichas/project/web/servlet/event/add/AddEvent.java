package com.github.adamovichas.project.web.servlet.event.add;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.service.data.impl.EventService;
import com.github.adamovichas.project.service.data.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addEvent", urlPatterns = {"/new_event/add_event/"})
public class AddEvent extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddEvent.class);

    @Autowired
    private IEventService eventService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.github.adamovichas.project.model.dto.EventDTO eventDTO = (com.github.adamovichas.project.model.dto.EventDTO) req.getAttribute("factor");
        boolean isExist = eventService.eventIsExist(eventDTO);
        if(!isExist){
            Long eventId = eventService.addEvent(eventDTO);
            EventDTO eventView = eventService.getEventById(eventId);
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
