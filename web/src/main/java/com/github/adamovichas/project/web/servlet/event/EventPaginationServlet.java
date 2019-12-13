package com.github.adamovichas.project.web.servlet.event;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.data.impl.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventPaginationServlet extends HttpServlet {

    @Autowired
    private IEventService eventService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentPage = req.getParameter("currentPage");
        if(currentPage == null){
            currentPage = "1";
        }
        int numberPage = Integer.parseInt(currentPage);
        req.setAttribute("currentPage",numberPage);
        List<EventView> events = eventService.getEventsOnCurrentPage(numberPage);
        req.setAttribute("eventsList",events);
        Long maxPages = eventService.getEventMaxPages();
        req.setAttribute("maxPages",maxPages);
        req.getRequestDispatcher("/event_pagination.jsp").forward(req,resp);
    }
}
