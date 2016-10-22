package by.hotel.command.client;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.entity.Account;
import by.hotel.entity.Booking;
import by.hotel.entity.User;
import by.hotel.service.AccountServiceImpl;
import by.hotel.service.BookingServiceImpl;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class PayCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(PayCommand.class);
        try {
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            String login = user.getLogin();
            BookingServiceImpl.getInstance().payBooking(bookingId);
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllBookingWithFinishedAccount(userId);
            request.setAttribute("bookingByUser", bookings);
            List<Account> accounts = AccountServiceImpl.getInstance().getAllAccountByUser(userId);
            request.setAttribute("accountById", accounts);
            request.setAttribute("paySuccess", MessageManager.getProperty("message.paySuccess"));
            page = ConfigurationManager.getProperty("path.page.myAccounts");
            LOG.info("User " + login + " paid his booking № " + bookingId);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
