package com.github.adamovichas.project.web.service;


import com.github.adamovichas.project.ICashAccountData;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.service.data.IDataCashAccountService;
import com.github.adamovichas.project.service.data.impl.DataBetService;
import com.github.adamovichas.project.service.data.impl.DataCashAccountService;
import com.github.adamovichas.project.service.data.impl.DataUserService;
import com.github.adamovichas.project.service.data.IdataUserService;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.model.dto.AuthUser;

import com.github.adamovichas.project.model.user.Role;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public final class Util {

    private static final IdataUserService serviceDAO = DataUserService.getInstance();
    private static final IDataBetService betData = DataBetService.getInstance();
    private static final IDataCashAccountService cashData = DataCashAccountService.getInstance();


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

    public static void setUserBetsInReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String login = authUser.getLogin();
        if(authUser.getRole().equals(Role.USER_VER)) {
            CashAccountDTO accountByLogin = cashData.getAccountByLogin(login);
            req.setAttribute("account",accountByLogin);
//            req.getRequestDispatcher("/bet_pagination").include(req,resp);
//            List<BetView> betViews = betData.getNotFinishedBetByLogin(login);
//            if(!betViews.isEmpty()) {
//                req.setAttribute("userBets", betViews);
//            }
        }
    }


}
