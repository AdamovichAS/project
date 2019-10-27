package com.github.adamovichas.project.web.service;


import com.github.adamovichas.project.service.data.impl.DataUserService;
import com.github.adamovichas.project.service.data.IdataUserService;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.MoneyDTO;

import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.IBetData;
import com.github.adamovichas.project.dao.impl.BetData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public final class Util {

    private static final IdataUserService serviceDAO = DataUserService.getInstance();
    private static final IBetData betData = BetData.BET_DATA;

    private Util() {
    }

    public static Cookie getCookie(String cookieName, Cookie[] cookies){
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

    public static void setUserBetsAndDepositInReq(HttpServletRequest req){
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String login = authUser.getLogin();
        List<BetView> betViews = betData.getNotFinishedBetByLogin(login);
        MoneyDTO deposit = betData.getMoneyByLogin(authUser.getLogin());
        if(!betViews.isEmpty()) {
            req.setAttribute("userBets", betViews);
        }
        if(authUser.getRole().equals(Role.USER_VER)) {
            req.setAttribute("deposit", deposit.getValue());
        }
    }


}
