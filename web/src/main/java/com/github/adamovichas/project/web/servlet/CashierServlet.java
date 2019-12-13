package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
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

import static java.util.Objects.nonNull;

@WebServlet(name = "CashierServlet", urlPatterns = {"/cashier"})
public class CashierServlet extends HttpServlet {
    @Autowired
    private ICashAccountService moneyService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if(authUser.getRole().equals(Role.USER_VER)) {
            CashAccountDTO account = moneyService.getAccountByLogin(authUser.getLogin());
            req.setAttribute("account", account);
        }
        String action = req.getParameter("action");
        if(nonNull(action)){
            req.setAttribute("action",action);
        }
        req.getRequestDispatcher("/cashier.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
