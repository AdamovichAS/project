package com.github.adamovichas.project.web.filter;

import com.github.adamovichas.project.service.data.IUserService;

import com.github.adamovichas.project.web.service.IServiceUtil;
import com.github.adamovichas.project.web.service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;
//
//@WebFilter(filterName = "CookieFilter",urlPatterns = {"/user_menu.jsp","/redirect","/admin_menu.jsp"})
//public class CookieFilter implements Filter {
//
//    public CookieFilter(IUserService userService, IServiceUtil serviceUtil) {
//        this.userService = userService;
//        this.serviceUtil = serviceUtil;
//    }
//
//    @Autowired
//    private final IUserService userService;
//
//    @Autowired
//    private final IServiceUtil serviceUtil;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        HttpSession session = req.getSession();
//        Cookie[] cookies = req.getCookies();
//        String cookieName = "login";
//        Cookie cookie = serviceUtil.getCookie(cookieName, cookies);
//        if (nonNull(cookie)) {
//            String cookieValue = cookie.getValue();
//            String[] loginPassword = cookieValue.split("/");
//            boolean checkUserLoginPassword = userService.userIsExist(loginPassword[0], loginPassword[1]);
//            if (checkUserLoginPassword) {
//                serviceUtil.setAuthUserInSession(session, loginPassword[0]);
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                cookie.setMaxAge(0);
//                res.addCookie(cookie);
//                req.getRequestDispatcher("login.jsp").forward(req, res);
//            }
//
//        }else {
//            req.getRequestDispatcher("login.jsp").forward(req, res);
//        }
//
//    }
//
//
//    @Override
//    public void destroy() {
//
//    }
//}
