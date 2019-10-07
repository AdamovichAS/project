package by.itacademy.jd2.servlets;


import by.itacademy.jd2.DAO.DAOUser;
import by.itacademy.jd2.DAO.IDAOUser;
import by.itacademy.jd2.user.AuthUser;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RoleRedirectServlet extends HttpServlet {
    private final IDAOUser daoUser = DAOUser.DAO_USER;
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String role = authUser.getRole().toString();
        if ("ADMIN".equals(role)) {
            // resp.sendRedirect("admin_menu.jsp");
            req.getRequestDispatcher("admin_menu.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("user_menu.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
