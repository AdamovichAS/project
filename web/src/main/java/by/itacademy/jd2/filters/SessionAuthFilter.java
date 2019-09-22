package by.itacademy.jd2.filters;


import by.itacademy.jd2.service.ServletsAndFilterService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class SessionAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        String login = (String) session.getAttribute("login");
        if (nonNull(login) && nonNull(role)) {
            req.getRequestDispatcher("/menu.jsp");
        //    ServletsAndFilterService.userRoleRedirect(role, req, res);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
