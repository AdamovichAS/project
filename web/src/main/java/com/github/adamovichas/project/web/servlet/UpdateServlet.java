package com.github.adamovichas.project.web.servlet;



import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.model.dto.AuthUser;

import com.github.adamovichas.project.service.util.IUtil;
import com.github.adamovichas.project.service.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateServlet.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private IUtil util;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,String>userFieldsForUpdate = new HashMap<>();
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String password = req.getParameter("password");
        userFieldsForUpdate.put("password",password);
        userFieldsForUpdate.put("firstName",req.getParameter("firstName"));
        userFieldsForUpdate.put("lastName",req.getParameter("lastName"));
        userFieldsForUpdate.put("phone",req.getParameter("phone"));
        userFieldsForUpdate.put("email",req.getParameter("email"));
        userFieldsForUpdate.put("country",req.getParameter("country"));
        util.removeEmptyValue(userFieldsForUpdate);
        if(!userFieldsForUpdate.isEmpty()){
            userService.updateUser(authUser.getLogin(),userFieldsForUpdate);
            if(password !=null) {
                resp.addCookie(new Cookie("login", authUser.getLogin() + "/" + password));
            }
            final UserDTO user = userService.getUserByLogin(authUser.getLogin());
            req.setAttribute("user",user);
            req.setAttribute("message","Update is done! ");
            log.info("Updated user {}", user);
            req.getRequestDispatcher("/update.jsp").forward(req,resp);
        }else {
            req.setAttribute("message","Update is failed! ");
            req.getRequestDispatcher("/update.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        UserDTO user = userService.getUserByLogin(authUser.getLogin());
        req.setAttribute("user",user);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }
}

