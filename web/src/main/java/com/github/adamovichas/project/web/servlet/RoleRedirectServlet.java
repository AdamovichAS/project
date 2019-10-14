package com.github.adamovichas.project.web.servlet;


import com.github.adamovichas.project.web.data.impl.DataBetService;
import com.github.adamovichas.project.web.data.impl.DataUserService;
import com.github.adamovichas.project.web.data.IDataBetService;
import com.github.adamovichas.project.web.data.IdataUserService;
import com.github.adamovichas.project.web.dto.AuthUser;
import com.github.adamovichas.project.web.service.Util;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RoleRedirectServlet extends HttpServlet {
    private IdataUserService daoUser;
    private IDataBetService dataBet;


    @Override
    public void init() throws ServletException {
        daoUser = DataUserService.getInstance();
        dataBet = DataBetService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String role = authUser.getRole().toString();
        if ("ADMIN".equals(role)) {
            // resp.sendRedirect("admin_menu.jsp");
            req.getRequestDispatcher("admin_menu.jsp").forward(req, resp);
        } else {
            Util.setUserBetsAndDepositInReq(req);
            req.getRequestDispatcher("user_menu.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
