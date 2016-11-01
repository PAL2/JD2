package com.hotel.command.user;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.impl.BookingServiceImpl;
import com.hotel.service.impl.UserServiceImpl;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(LoginCommand.class);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            User user = UserServiceImpl.getInstance().logIn(login, password);
            request.getSession().setAttribute("user", user);
            LOG.info("On the site came user with login : " + login);
            try {
                if (user.getUserRole().equalsIgnoreCase("admin")) {
                    page = ConfigurationManager.getProperty("path.page.admin");
                    request.getSession().setAttribute("isAdmin", true);
                    List<Booking> bookings = BookingServiceImpl.getInstance().getAllNewBooking();
                    request.setAttribute("newBooking", bookings);
                } else {
                    page = ConfigurationManager.getProperty("path.page.order");
                }
            } catch (ServiceException e) {
                page = ConfigurationManager.getProperty("path.page.errorDatabase");
                request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
            }
        } catch (NullPointerException e) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
            page = ConfigurationManager.getProperty("path.page.login");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}