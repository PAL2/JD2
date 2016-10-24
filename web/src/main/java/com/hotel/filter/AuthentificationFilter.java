package com.hotel.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthentificationFilter
 */
public class AuthentificationFilter implements Filter {
    List<String> withoutReg;

    /**
     * Default constructor.
     */
    public AuthentificationFilter() {
    }


    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String URI = request.getRequestURI();
        boolean isWithoutReg = false;
        for (String s : withoutReg) {
            if (URI.contains(s) && (!s.equals("/")) || withoutReg.contains(URI)) {
                isWithoutReg = true;
            }
        }
        if (!isWithoutReg && (session == null || session.getAttribute("user") == null)) {
            response.sendRedirect("/success.jsp");
            //  } else if (!isWithoutReg && (session == null) || session.getAttribute("user") == null)){
            //    response.sendRedirect("/success.jsp");
        } else {
            // place your code here

            // pass the request along the filter chain
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        withoutReg = new ArrayList<>(3);
        withoutReg.add("/index.jsp");
        withoutReg.add("/login.jsp");
        withoutReg.add("/reg.jsp");
    }

}
