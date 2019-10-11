package com.github.adamovichas.servlet;

import com.github.adamovichas.dto.AuthUser;
import com.github.adamovichas.service.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class LogOutServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LogOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie loginCookie = Util.getCookie("login", req.getCookies());
        loginCookie.setMaxAge(0);
        resp.addCookie(loginCookie);
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        req.getSession().removeAttribute("authUser");
        log.info("user {} logout",authUser.getLogin());
        resp.sendRedirect("http://localhost:8080/project/logout.jsp");
    }
}

