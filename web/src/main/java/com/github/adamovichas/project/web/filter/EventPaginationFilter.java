package com.github.adamovichas.project.web.filter;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.service.data.IEventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventPaginationFilter implements Filter {
    @Autowired
    private IEventService eventService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String currentPage = req.getParameter("currentPage");
        if(currentPage == null){
            currentPage = "1";
        }
        int numberPage = Integer.parseInt(currentPage);
        req.setAttribute("currentPage",numberPage);
        List<EventDTO> events = eventService.getEventsOnCurrentPage(numberPage);
        req.setAttribute("eventsList",events);
        Long maxPages = eventService.getEventMaxPages();
        req.setAttribute("maxPages",maxPages);
        filterChain.doFilter(req,res);

    }

    @Override
    public void destroy() {

    }
}
