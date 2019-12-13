package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.service.data.ICashAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WithdrawalServlet", urlPatterns = {"/withdrawal"})
public class WithdrawalServlet extends HttpServlet {
    @Autowired
    private ICashAccountService moneyService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int withdrawal = Integer.parseInt(req.getParameter("withdrawal"));
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        boolean isWithdrawalDone = moneyService.withdrawal(authUser.getLogin(), withdrawal);
        String message;
        if(isWithdrawalDone){
            message = String.format("withdrawal.done",withdrawal);
        }else {
            message = "withdrawal.failed";
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("/cashier").include(req,resp);
    }
}
