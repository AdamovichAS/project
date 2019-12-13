package com.github.adamovichas.project.web.servlet;


import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.web.service.IServiceUtil;
import com.github.adamovichas.project.web.service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RoleRedirectServlet", urlPatterns = {"/redirect"})
public class RoleRedirectServlet extends HttpServlet {

    @Autowired
    private IServiceUtil serviceUtil;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String role = authUser.getRole().toString();
        if ("ADMIN".equals(role)) {
            // resp.sendRedirect("admin_menu.jsp");
            req.getRequestDispatcher("admin_menu.jsp").forward(req, resp);
        } else {
            serviceUtil.setUserBetsInReq(req,resp);
            req.getRequestDispatcher("user_menu.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
