package com.github.adamovichas.service;


import com.github.adamovichas.data.impl.DataUserService;
import com.github.adamovichas.data.IdataUserService;
import com.github.adamovichas.dto.BetView;
import com.github.adamovichas.dto.AuthUser;
import com.github.adamovichas.dto.Money;

import com.github.adamovichas.mysql_data.IBetData;
import com.github.adamovichas.mysql_data.impl.BetData;
import com.github.adamovichas.user.Role;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public final class Util {
    private static final IdataUserService serviceDAO = DataUserService.DATA_USER_SERVICE;
    private static final IBetData betData = BetData.BET_DATA;
    /* public static void userRoleRedirect(String userRole, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (userRole) {
            case "USER":
                request.getRequestDispatcher("user_menu.jsp").forward(request, response);
                break;
            case "ADMIN":
                List<String> usersLogins = DataUserService.DATA_USER_SERVICE.getUsersLogin(User.ROLE.USER);
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

    public static void setUserBetDepositInReq(HttpServletRequest req){
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String login = authUser.getLogin();
        List<BetView> betViews = betData.getNotFinishedBetByLogin(login);
        Money deposit = betData.getMoneyByLogin(authUser.getLogin());
        if(!betViews.isEmpty()) {
            req.setAttribute("userBets", betViews);
        }
        req.setAttribute("deposit",deposit.getValue());
    }


}
