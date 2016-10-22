package by.hotel.command.user;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.entity.Booking;
import by.hotel.entity.User;
import by.hotel.entity.UserEntity;
import by.hotel.service.BookingServiceImpl;
import by.hotel.service.UserServiceImpl;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(LoginCommand.class);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            UserEntity user = UserServiceImpl.getInstance().logIn(login, password);
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
            } catch (ServiceException | SQLException e) {
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