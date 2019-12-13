package com.github.adamovichas.project.web.controller;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.web.service.IServiceUtil;
import com.github.adamovichas.project.web.servlet.LoginServlet;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Controller
public class MainPageController {

    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);

    private final IEventService eventService;
    private final IUserService userService;
    private final IServiceUtil serviceUtil;

    public MainPageController(IEventService eventService, IUserService userService, IServiceUtil serviceUtil) {
        this.eventService = eventService;
        this.userService = userService;
        this.serviceUtil = serviceUtil;
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

    @GetMapping(value = "/login")
    public String loginGet(HttpServletRequest req) {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginPost(HttpServletRequest req){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.userIsExist(login, password)) {
            AuthUser authUser = serviceUtil.setAuthUserInSession(req, login);
//            Cookie cookieLogin = new Cookie("login", login + "/" + password);
//            response.addCookie(cookieLogin);
            log.info("user {} logIn at {}", login, LocalDateTime.now());
            if(authUser.getRole().equals(Role.ADMIN)){
                return "admin_menu";
            }else {
                return "user_menu";
            }
//            req.getRequestDispatcher("/redirect").forward(req,response);
        }else {
            req.setAttribute("wrongLogin", "User with this Login and password does not exist");
            log.info("user {} Wrong logIn at {}", login, LocalDateTime.now());
            return "login";
        }
    }
}
