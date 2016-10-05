package by.hotel.command.admin;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.entity.Booking;
import by.hotel.service.BookingServiceImpl;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class RejectCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(RejectCommand.class);
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.admin");
            BookingServiceImpl.getInstance().rejectBooking(bookingId);
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllNewBooking();
            request.setAttribute("newBooking", bookings);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        LOG.info("Administrator reject the book № " + bookingId);
        return page;
    }

}
