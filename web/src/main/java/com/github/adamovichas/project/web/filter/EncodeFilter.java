package com.github.adamovichas.project.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodeFilter implements Filter {

    private static final String UTF_8 = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.setCharacterEncoding(UTF_8);
    }

    @Override
    public void destroy() {

    }
}
