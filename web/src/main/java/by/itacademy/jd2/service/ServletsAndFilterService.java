package by.itacademy.jd2.service;


import by.itacademy.jd2.DAO.IDAOUser;
import by.itacademy.jd2.DAO.DAOUser;
import by.itacademy.jd2.user.AuthUser;
import by.itacademy.jd2.user.Role;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public final class ServletsAndFilterService {
    private static final IDAOUser serviceDAO = DAOUser.DAO_USER;
    /* public static void userRoleRedirect(String userRole, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (userRole) {
            case "USER":
                request.getRequestDispatcher("user_menu.jsp").forward(request, response);
                break;
            case "ADMIN":
                List<String> usersLogins = DAOUser.DAO_USER.getUsersLogin(User.ROLE.USER);
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
        Role role = serviceDAO.getUserByLogin(login).getRole();
        AuthUser authUser = new AuthUser(login,role);
        session.setAttribute("authUser", authUser);

    }
}
