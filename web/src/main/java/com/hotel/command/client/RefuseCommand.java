package com.hotel.command.client;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.AccountServiceImpl;
import com.hotel.service.BookingServiceImpl;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class RefuseCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(RefuseCommand.class);
        try {
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            String login = user.getLogin();
            BookingServiceImpl.getInstance().refuseBooking(bookingId);
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllBookingWithFinishedAccount(userId);
            request.setAttribute("bookingByUser", bookings);
            List<AccountEntity> accounts = AccountServiceImpl.getInstance().getAllAccountByUser(userId);
            request.setAttribute("accountById", accounts);
            page = ConfigurationManager.getProperty("path.page.myAccounts");
            LOG.info("User " + login + " rejected the bill book № " + bookingId);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
