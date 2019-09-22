package by.itacademy.jd2.servlets;

import by.itacademy.jd2.ServiceDAO.IServiceDAO;
import by.itacademy.jd2.ServiceDAO.ServiceDAO;
import by.itacademy.jd2.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoleRedirectServlet extends HttpServlet {
    private final IServiceDAO serviceDAO = ServiceDAO.SERVICE_DATA_USER;
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");
        switch (role) {
            case "USER":
                req.getRequestDispatcher("user_menu.jsp").forward(req, resp);
                break;
            case "ADMIN":
                List<String> usersLogins = serviceDAO.getUsersLogin(User.ROLE.USER);
                req.getSession().setAttribute("listLogins",usersLogins);
               // resp.sendRedirect("admin_menu.jsp");
                req.getRequestDispatcher("admin_menu.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
