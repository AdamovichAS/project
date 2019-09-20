package by.itacademy.jd2.service;

import by.itacademy.jd2.ServiceDAO.DataService;
import by.itacademy.jd2.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public final class ServletsAndFilterService {
   /* public static void userRoleRedirect(String userRole, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (userRole) {
            case "USER":
                request.getRequestDispatcher("user_menu.jsp").forward(request, response);
                break;
            case "ADMIN":
                List<String> usersLogins = DataService.SERVICE_DATA_USER.getUsersLogin(User.ROLE.USER);
                request.getSession().setAttribute("listLogins",usersLogins);
                request.getRequestDispatcher("admin_menu.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
        }
    }*/

    public static Cookie getCookie(String cookieName,Cookie[] cookies){
        Cookie resultCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                resultCookie = cookie;
                break;
            }
        }
        return resultCookie;
    }

    public static void setRoleLoginInSession(HttpSession session, String login){
        User user = DataService.SERVICE_DATA_USER.getUserByLogin(login);
        session.setAttribute("login", user.getLogin());
        session.setAttribute("role", user.getRole().toString());
    }
}
