package com.github.adamovichas.servlet;

import com.github.adamovichas.DAO.DAOUser;
import com.github.adamovichas.DAO.impl.IDAOUser;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
    private final IDAOUser daoUser = DAOUser.DAO_USER;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("repeatedPassword");
        if(daoUser.loginIsExist(login) || !password.equals(repeatedPassword)){
            req.setAttribute("loginError","A user with this login already exists or the passwords do not match");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }else{
            Map<String,String> userParam = new HashMap<>();
            userParam.put("login",login);
            userParam.put("password",password);
            userParam.put("firstName",req.getParameter("firstName"));
            userParam.put("lastName",req.getParameter("lastName"));
            userParam.put("phone",req.getParameter("phone"));
            userParam.put("email",req.getParameter("email"));
            userParam.put("age",req.getParameter("age"));
            userParam.put("country",req.getParameter("country"));
            daoUser.addNewUser(userParam);
            getServletContext().getRequestDispatcher("/login").forward(req,resp);
        }
    }
}
