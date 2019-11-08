package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.MoneyDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.service.data.IDataMoneyService;
import com.github.adamovichas.project.service.data.impl.DataMoneyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class CashierServlet extends HttpServlet {

    private static final IDataMoneyService moneyService = DataMoneyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if(authUser.getRole().equals(Role.USER_VER)) {
            MoneyDTO account = moneyService.getMoneyByLogin(authUser.getLogin());
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
