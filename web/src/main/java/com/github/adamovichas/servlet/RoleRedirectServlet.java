package com.github.adamovichas.servlet;


import com.github.adamovichas.data.impl.DataBetService;
import com.github.adamovichas.data.impl.DataUserService;
import com.github.adamovichas.data.IdataBetService;
import com.github.adamovichas.data.IdataUserService;
import com.github.adamovichas.service.Util;
import com.github.adamovichas.dto.AuthUser;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RoleRedirectServlet extends HttpServlet {
    private IdataUserService daoUser;
    private IdataBetService dataBet;


    @Override
    public void init() throws ServletException {
        daoUser = DataUserService.DATA_USER_SERVICE;
        dataBet = DataBetService.DATA_BET_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String role = authUser.getRole().toString();
        if ("ADMIN".equals(role)) {
            // resp.sendRedirect("admin_menu.jsp");
            req.getRequestDispatcher("admin_menu.jsp").forward(req, resp);
        } else {
            Util.setUserBetDepositInReq(req);
            req.getRequestDispatcher("user_menu.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
