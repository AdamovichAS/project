package com.github.adamovichas.project.web.servlet;


import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.web.service.IServiceUtil;
import com.github.adamovichas.project.web.service.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogOutServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LogOutServlet.class);

    @Autowired
    private IServiceUtil serviceUtil;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie loginCookie = serviceUtil.getCookie("login", req.getCookies());
        loginCookie.setMaxAge(0);
        resp.addCookie(loginCookie);
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        req.getSession().removeAttribute("authUser");
        log.info("user {} logout",authUser.getLogin());
        resp.sendRedirect("http://localhost:8080/project/logout.jsp");
    }
}

