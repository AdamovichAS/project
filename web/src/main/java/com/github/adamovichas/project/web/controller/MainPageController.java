package com.github.adamovichas.project.web.controller;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.web.service.IServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainPageController {

    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);

    private final IEventService eventService;

    public MainPageController(IEventService eventService) {
        this.eventService = eventService;

    }

    @GetMapping(value = "/main")
    public String doGet(HttpServletRequest req) {
        String currentPage = req.getParameter("currentPage");
        if (currentPage == null) {
            currentPage = "1";
        }
        int numberPage = Integer.parseInt(currentPage);
        req.setAttribute("currentPage", numberPage);
        List<EventView> events = eventService.getEventsOnCurrentPage(numberPage);
        req.setAttribute("eventsList", events);
        Long maxPages = eventService.getEventMaxPages();
        req.setAttribute("maxPages", maxPages);
        return "main";
    }

}
