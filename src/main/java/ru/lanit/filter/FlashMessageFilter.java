package ru.lanit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FlashMessageFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        prepareMessage(httpRequest,"errorMessage");
        prepareMessage(httpRequest,"successMessage");

        chain.doFilter(request,response);
    }

    private void prepareMessage(HttpServletRequest httpRequest,String messageType){
        String message = (String)httpRequest.getSession().getAttribute(messageType);
        if(message != null && !message.isEmpty()){
            httpRequest.setAttribute(messageType, message);
            httpRequest.getSession().removeAttribute(messageType);
        }
    }
}
