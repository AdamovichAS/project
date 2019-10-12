package com.github.adamovichas.servlet;

import com.github.adamovichas.data.impl.DataUserService;
import com.github.adamovichas.data.IdataUserService;
import com.github.adamovichas.service.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;


public class LoginServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private final IdataUserService daoUser = DataUserService.DATA_USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (daoUser.userIsExist(login, password)) {
            HttpSession session = request.getSession();
            Util.setAuthUserInSession(session,login);
            Cookie cookieLogin = new Cookie("login", login + "/" + password);
            response.addCookie(cookieLogin);
            request.getRequestDispatcher("/redirect").forward(request,response);
            log.info("user {} logIn at {}", login, LocalDateTime.now());
        }else {
            request.setAttribute("wrongLogin", "User with this Login and password does not exist");
            log.info("user {} Wrong logIn at {}", login, LocalDateTime.now());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}