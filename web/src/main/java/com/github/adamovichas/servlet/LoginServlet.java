package com.github.adamovichas.servlet;

import com.github.adamovichas.DAO.DAOUser;
import com.github.adamovichas.DAO.impl.IDAOUser;
import com.github.adamovichas.service.Util;


import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private final IDAOUser daoUser = DAOUser.DAO_USER;

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
        }else {
            request.setAttribute("wrongLogin", "User with this Login and password does not exist");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}