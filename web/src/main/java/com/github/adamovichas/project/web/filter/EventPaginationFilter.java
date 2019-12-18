package com.github.adamovichas.project.web.filter;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.IEventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebFilter(filterName = "EventPaginationFilter",urlPatterns = {"/event_pagination.jsp"})
//public class EventPaginationFilter implements Filter {
//    @Autowired
//    private IEventService eventService;
//
//    public EventPaginationFilter(IEventService eventService) {
//        this.eventService = eventService;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        String currentPage = req.getParameter("currentPage");
//        if(currentPage == null){
//            currentPage = "1";
//        }
//        int numberPage = Integer.parseInt(currentPage);
//        req.setAttribute("currentPage",numberPage);
//        List<EventView> events = eventService.getEventsOnCurrentPageByResultFactorId(numberPage);
//        req.setAttribute("eventsList",events);
//        Long maxPages = eventService.getEventMaxPages();
//        req.setAttribute("maxPages",maxPages);
//        filterChain.doFilter(req,res);
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
