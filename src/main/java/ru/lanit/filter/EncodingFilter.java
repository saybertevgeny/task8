package ru.lanit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        httpRequest.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }
}
