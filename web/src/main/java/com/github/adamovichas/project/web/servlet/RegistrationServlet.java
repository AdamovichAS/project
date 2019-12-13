package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.service.data.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegistrationServlet",urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);
    @Autowired
    private IUserService daoUser;

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
            log.info("user created:{} at {}", login, LocalDateTime.now());
            getServletContext().getRequestDispatcher("/login").forward(req,resp);
        }
    }
}
