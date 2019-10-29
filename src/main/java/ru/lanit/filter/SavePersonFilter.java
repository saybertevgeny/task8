package ru.lanit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SavePersonFilter implements Filter {

    private enum RequiredParams{firstname,middlename,lastname,birthday}

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpRequest.setCharacterEncoding("UTF-8");
        boolean toNext = true;

        for(RequiredParams p: RequiredParams.values()){
            String paramValue = httpRequest.getParameter(p.name());
            if(paramValue == null || paramValue.isEmpty()){
                httpRequest.getSession().setAttribute("errorMessage","Все поля обязательны");
                toNext = false;
                break;
            }
        }
        if(toNext) {
            chain.doFilter(request,response);
        }
        else{
            httpResponse.sendRedirect("/");
        }
    }
}
