package by.hotel.filter;

import by.hotel.command.ActionCommand;
import by.hotel.command.CommandEnum;
import by.hotel.command.ConfigurationManager;
import by.hotel.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Алексей on 04.10.2016.
 */
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String action = httpRequest.getParameter("command");
        ActionCommand current;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        User user = (User) httpRequest.getSession().getAttribute("user");
        String userRole = user.getUserRole();
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            if (userRole == null) {
                if (currentEnum == CommandEnum.LOGIN) {
                    chain.doFilter(request, response);
                } else if (currentEnum == CommandEnum.REG) {
                    chain.doFilter(request, response);
                } else {
                    String page = ConfigurationManager.getProperty("path.page.index");
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(httpRequest, httpResponse);
                    session.invalidate();
                }
            } else {
                chain.doFilter(request, response);

            }
        } catch (IllegalArgumentException e) {
            String page = ConfigurationManager.getProperty("path.page.index");
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(httpRequest, httpResponse);
        }
    }
}