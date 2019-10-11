package com.github.adamovichas.servlet;

import com.github.adamovichas.service.Util;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie loginCookie = Util.getCookie("login", req.getCookies());
        loginCookie.setMaxAge(0);
        resp.addCookie(loginCookie);
        req.getSession().removeAttribute("authUser");
        resp.sendRedirect("http://localhost:8080/project/logout.jsp");
    }
}

