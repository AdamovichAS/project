package com.github.adamovichas.project.web.filter;



import com.github.adamovichas.project.model.dto.AuthUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;
//@WebFilter(filterName = "SessionAuthFilter",urlPatterns = {"/user_menu.jsp","/redirect","/admin_menu.jsp"})
//public class SessionAuthFilter implements Filter {
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        HttpSession session = req.getSession();
//        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
//        if (nonNull(authUser)) {
//            req.getRequestDispatcher("/redirect").forward(req,res);
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
