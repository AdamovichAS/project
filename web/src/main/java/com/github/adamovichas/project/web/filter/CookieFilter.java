package com.github.adamovichas.project.web.filter;

import com.github.adamovichas.project.web.data.impl.DataUserService;
import com.github.adamovichas.project.web.data.IdataUserService;

import com.github.adamovichas.project.web.service.Util;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class CookieFilter implements Filter {
    private IdataUserService serviceDAO;

    @Override
    public void init(FilterConfig filterConfig) {
        serviceDAO = DataUserService.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        String cookieName = "login";
        Cookie cookie = Util.getCookie(cookieName, cookies);
        if (nonNull(cookie)) {
            String cookieValue = cookie.getValue();
            String[] loginPassword = cookieValue.split("/");
            boolean checkUserLoginPassword = serviceDAO.userIsExist(loginPassword[0], loginPassword[1]);
            if (checkUserLoginPassword) {
                Util.setAuthUserInSession(session, loginPassword[0]);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                cookie.setMaxAge(0);
                res.addCookie(cookie);
                req.getRequestDispatcher("login.jsp").forward(req, res);
            }

        }else {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }

    }


    @Override
    public void destroy() {

    }
}
