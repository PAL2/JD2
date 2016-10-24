package com.hotel.filter;

import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Алексей on 19.10.2016.
 */
//@WebFilter(filterName = "ClosingSessionFilter")
public class ClosingSessionFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Session session = HibernateUtil.getInstance().getSession();
        chain.doFilter(request, response);
        HibernateUtil.getInstance().releaseSession(session);
    }

    public void destroy() {
    }

}
