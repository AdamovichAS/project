package by.itacademy.jd2.servlets;

import by.itacademy.jd2.ServiceDAO.DataService;
import by.itacademy.jd2.service.ServletsAndFilterService;
import by.itacademy.jd2.user.User;



import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (DataService.SERVICE_DATA_USER.userIsExist(login, password)) {
            HttpSession session = request.getSession();
            ServletsAndFilterService.setRoleLoginInSession(session,login);
            Cookie cookieLogin = new Cookie("login", login + "/" + password);
            response.addCookie(cookieLogin);
            request.getRequestDispatcher("/redirect").forward(request,response);
        }else {
            request.setAttribute("wrongLogin", "User with this Login and password does not exist");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}