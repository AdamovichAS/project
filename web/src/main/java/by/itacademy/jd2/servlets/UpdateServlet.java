package by.itacademy.jd2.servlets;

import by.itacademy.jd2.ServiceDAO.IServiceDAO;
import by.itacademy.jd2.ServiceDAO.ServiceDAO;
import by.itacademy.jd2.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateServlet extends HttpServlet {
    private final IServiceDAO serviceDAO = ServiceDAO.SERVICE_DATA_USER;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.removeAttribute("user");
        Map<String,String>userFieldsForUpdate = new HashMap<>();
        String login = (String) req.getSession().getAttribute("login");
        String password = req.getParameter("password");
        userFieldsForUpdate.put("password",password);
        String money = req.getParameter("money");
        userFieldsForUpdate.put("money",money);
        if(serviceDAO.updateUserInfo(userFieldsForUpdate,login)){
            User user = serviceDAO.getUserByLogin(login);
            resp.addCookie(new Cookie("login",user.getLogin()+"/"+user.getPassword()));
            resp.getWriter().write("Update is done, new info: " + user );
        }else {
            resp.getWriter().write("Update is failed");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = serviceDAO.getUserByLogin((String) req.getSession().getAttribute("login"));
        req.setAttribute("user",user);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }
}

