package com.github.adamovichas.project.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String locale = req.getParameter("locale");
        if (locale == null) {
            req.getSession().setAttribute("locale", "en_EN");
        } else {
            req.getSession().setAttribute("locale", locale);
        }
        filterChain.doFilter(req,res);
    }

}
