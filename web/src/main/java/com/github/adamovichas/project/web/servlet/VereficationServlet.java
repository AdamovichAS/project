package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.service.data.ICashAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VerificationServlet", urlPatterns = {"/verification"})
public class VereficationServlet extends HttpServlet {
    @Autowired
    private ICashAccountService cashAccountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        boolean isCreated = cashAccountService.verification(authUser.getLogin());
        String message;
        if(isCreated){
            authUser.setRole(Role.USER_VER);
            message = "addUserCashAccount.done";
        }else {
            message = "addUserCashAccount.false";
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("/cashier.jsp").forward(req,resp);
    }
}
