package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.service.data.ICashAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "DepositServlet", urlPatterns = {"/deposit"})
//public class DepositServlet extends HttpServlet {
//    @Autowired
//    private ICashAccountService cashAccountService;
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        double deposit = Double.parseDouble(req.getParameter("deposit"));
//        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
//        boolean isDepositDone = cashAccountService.makeDeposit(authUser.getLogin(), deposit);
//        String message;
//        if(isDepositDone){
//            message = String.format("deposit.done",deposit);
//        }else {
//            message = "makeDeposit.failed";
//        }
//        req.setAttribute("message",message);
//        req.getRequestDispatcher("/cashier").include(req,resp);
//    }
//}
