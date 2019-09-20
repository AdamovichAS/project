package by.itacademy.jd2.servlets;

import by.itacademy.jd2.service.ServletsAndFilterService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie loginCookie = ServletsAndFilterService.getCookie("login", req.getCookies());
        loginCookie.setMaxAge(0);
        resp.addCookie(loginCookie);
        req.getSession().removeAttribute("login");
        req.getSession().removeAttribute("role");
        resp.sendRedirect("http://localhost:8080/project/logout.jsp");
    }
}

