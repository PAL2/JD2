package com.hotel.command.client;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.BookingEntity;
import com.hotel.entity.User;
import com.hotel.service.BookingServiceImpl;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class MyBookingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(MyBookingCommand.class);
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserId();
        String login = user.getLogin();
        try {
            page = ConfigurationManager.getProperty("path.page.myBookings");
            List<BookingEntity> bookings = BookingServiceImpl.getInstance().getAllBookingByUser(userId);
            request.setAttribute("bookingByUser", bookings);
            LOG.info("User " + login + " looked up a list of his bookings");
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
