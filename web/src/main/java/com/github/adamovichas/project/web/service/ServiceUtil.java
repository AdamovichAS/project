package com.github.adamovichas.project.web.service;


import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.model.dto.AuthUser;

import com.github.adamovichas.project.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServiceUtil implements IServiceUtil{

    @Autowired
    private final IUserService userService;


    public ServiceUtil(IUserService userService) {
        this.userService = userService;

    }

    @Override
    public Cookie getCookie(String cookieName, Cookie[] cookies){
        Cookie resultCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                resultCookie = cookie;
                break;
            }
        }
        return resultCookie;
    }

    @Override
    public AuthUser setAuthUserInSession(HttpServletRequest request, String login){
        HttpSession session = request.getSession();
        Role role = userService.getUserByLogin(login).getRole();
        AuthUser authUser = new AuthUser(login,role);
        session.setAttribute("authUser", authUser);
        return authUser;
    }

    @Override
    public void setUserBetsInReq(HttpServletRequest req, HttpServletResponse resp) {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String login = authUser.getLogin();
        if(authUser.getRole().equals(Role.USER_VER)) {
            CashAccountDTO accountByLogin = userService.getCashAccountByLogin(login);
            req.setAttribute("account",accountByLogin);
//            req.getRequestDispatcher("/bet_pagination").include(req,resp);
//            List<BetView> betViews = betService.getAllByUserAndStatus(login);
//            if(!betViews.isEmpty()) {
//                req.setAttribute("userBets", betViews);
//            }
        }
    }


}
