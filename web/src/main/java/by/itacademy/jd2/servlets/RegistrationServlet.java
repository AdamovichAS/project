package by.itacademy.jd2.servlets;

import by.itacademy.jd2.ServiceDAO.DataService;
import by.itacademy.jd2.user.User;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("repeatedPassword");
        int money = Integer.parseInt(req.getParameter("money"));
        if(nonNull(DataService.SERVICE_DATA_USER.getUserByLogin(login)) || !password.equals(repeatedPassword)){
            req.setAttribute("loginError","A user with this login already exists or the passwords do not match");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }else{
            User user = new User(login,password,money, User.ROLE.USER);
            DataService.SERVICE_DATA_USER.addNewUser(user);
            getServletContext().getRequestDispatcher("/login").forward(req,resp);
        }
    }
}
