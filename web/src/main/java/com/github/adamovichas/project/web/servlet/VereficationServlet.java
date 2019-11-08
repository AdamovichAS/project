package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.service.data.IDataCashAccountService;
import com.github.adamovichas.project.service.data.impl.DataCashAccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VereficationServlet extends HttpServlet {

    private IDataCashAccountService moneyService = DataCashAccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        boolean isCreated = moneyService.verification(authUser.getLogin());
        String message;
        if(isCreated){
            authUser.setRole(Role.USER_VER);
            message = "verification.done";
        }else {
            message = "verification.false";
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("/cashier.jsp").forward(req,resp);
    }
}
