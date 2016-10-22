package by.hotel.command.client;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.entity.Booking;
import by.hotel.entity.UserEntity;
import by.hotel.service.BookingServiceImpl;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class MyBookingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(MyBookingCommand.class);
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        int userId = user.getUserId();
        String login = user.getLogin();
        try {
            page = ConfigurationManager.getProperty("path.page.myBookings");
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllBookingByUser(userId);
            request.setAttribute("bookingByUser", bookings);
            LOG.info("User " + login + " looked up a list of his bookings");
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
