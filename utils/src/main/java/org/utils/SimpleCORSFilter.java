package org.utils;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCORSFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
            throws IOException, ServletException {
        HttpServletResponse http = (HttpServletResponse) response;
        http.setHeader("Access-Control-Allow-Origin", "*");
        http.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH");
        http.setHeader("Access-Control-Max-Age", "3600");
        http.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
        filter.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) {

    }

    public void destroy() {

    }
}
