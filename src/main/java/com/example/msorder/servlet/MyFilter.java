package com.example.msorder.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String parameterLoc = servletRequest.getParameter("name");

        if ("ali".equalsIgnoreCase(parameterLoc)) {
            System.out.println("Filtre çalıştı");
            servletResponse.getWriter().println("Geçemezsin!");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
