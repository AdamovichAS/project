package by.itacademy.jd2.filters;

import by.itacademy.jd2.ServiceDAO.DataService;
import by.itacademy.jd2.service.ServletsAndFilterService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class CookieFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        String cookieName = "login";
        Cookie cookie = ServletsAndFilterService.getCookie(cookieName, cookies);
        if (nonNull(cookie)) {
            String cookieValue = cookie.getValue();
            String[] loginPassword = cookieValue.split("/");
            boolean checkUserLoginPassword = DataService.SERVICE_DATA_USER.userIsExist(loginPassword[0], loginPassword[1]);
            if (checkUserLoginPassword) {
                ServletsAndFilterService.setRoleLoginInSession(session, loginPassword[0]);
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
