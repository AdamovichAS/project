package com.github.adamovichas.project.web.service;


import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.model.dto.AuthUser;

import com.github.adamovichas.project.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class WebUtil {

//    private final IUserService userService;
//
//
//    public ServiceUtil(IUserService userService) {
//        this.userService = userService;
//
//    }
//
//    @Override
//    public AuthUser getAuthUserInDB(String login){
//        Role role = userService.getUserByLogin(login).getRole();
//        return new AuthUser(login,role);
//    }
//
//    @Override
//    public AuthUser setAuthUserInSession(HttpServletRequest request, String login){
//        HttpSession session = request.getSession();
//        Role role = userService.getUserByLogin(login).getRole();
//        AuthUser authUser = new AuthUser(login,role);
//        session.setAttribute("authUser", authUser);
//        return authUser;
//    }
//    @Override
    public static AuthUser getUserInSession() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (AuthUser) authentication.getPrincipal();
    }


}
