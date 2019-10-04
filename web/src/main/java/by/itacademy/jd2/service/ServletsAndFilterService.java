package by.itacademy.jd2.service;


import by.itacademy.jd2.ServiceDAO.IServiceDAO;
import by.itacademy.jd2.ServiceDAO.ServiceDAO;
import by.itacademy.jd2.user.AuthUser;
import by.itacademy.jd2.user.Role;
import by.itacademy.jd2.user.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public final class ServletsAndFilterService {
    private static final IServiceDAO serviceDAO = ServiceDAO.SERVICE_DATA_USER;
    /* public static void userRoleRedirect(String userRole, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (userRole) {
            case "USER":
                request.getRequestDispatcher("user_menu.jsp").forward(request, response);
                break;
            case "ADMIN":
                List<String> usersLogins = ServiceDAO.SERVICE_DATA_USER.getUsersLogin(User.ROLE.USER);
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

    public static void setAuthUserInSession(HttpSession session, String login){
        Role role = Role.valueOf(serviceDAO.getUserRoleByLogin(login));
        AuthUser authUser = new AuthUser(login,role);
        session.setAttribute("authUser", authUser);

    }
}
