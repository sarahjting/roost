package com.sarahjting.roost.common.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    public SimpleCorsFilter() {
        super();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // Wild-card origin does not work if credentials are enabled!
        // httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With, WWW-Authenticate, Authorization, Origin, Content-Type, Version");
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "X-Requested-With, WWW-Authenticate, Authorization, Origin, Content-Type");

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
