package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.service.data.IDataMoneyService;
import com.github.adamovichas.project.service.data.impl.DataMoneyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepositServlet extends HttpServlet {

    private IDataMoneyService moneyService = DataMoneyService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer deposit = Integer.parseInt(req.getParameter("deposit"));
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        boolean isDepositDone = moneyService.deposit(authUser.getLogin(), deposit);
        String message;
        if(isDepositDone){
            message = String.format("deposit.done",deposit);
        }else {
            message = "deposit.failed";
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("/cashier").include(req,resp);
    }
}
