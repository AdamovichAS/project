package com.github.adamovichas.project.web.controller;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.web.service.IServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class AuthentificationController {

    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);

    private final IUserService userService;
    private final IServiceUtil serviceUtil;

    public AuthentificationController(IUserService userService, IServiceUtil serviceUtil) {
        this.userService = userService;
        this.serviceUtil = serviceUtil;
    }

    @GetMapping(value = "/login")
    public String loginGet() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginPost(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.userIsExist(login, password)) {
            serviceUtil.setAuthUserInSession(req, login);
            log.info("user {} logIn at {}", login, LocalDateTime.now());
            return "redirect:/my_page";
        } else {
            req.setAttribute("wrongLogin", "User with this Login and password does not exist");
            log.info("user {} Wrong logIn at {}", login, LocalDateTime.now());
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest req){
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        req.getSession().removeAttribute("authUser");
        req.getSession().invalidate();
        log.info("User login: {} logged out at: {}", authUser.getLogin(), LocalDateTime.now());
        return "login";
    }

    @RequestMapping(value = "/my_page")
    public String redirectAfterLogin(HttpServletRequest req) {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if (Role.ADMIN.equals(authUser.getRole())) {
            return "admin_menu";
        } else {
            serviceUtil.setUserBetsInReq(req);
            return "user_menu";
        }
    }
}
