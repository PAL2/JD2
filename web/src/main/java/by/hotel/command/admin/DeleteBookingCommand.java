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

public class DeleteBookingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(DeleteBookingCommand.class);
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        try {
            page = ConfigurationManager.getProperty("path.page.allBookings");
            BookingServiceImpl.getInstance().delete(bookingId);
            List<Booking> bookings = BookingServiceImpl.getInstance().getAll();
            request.setAttribute("allBooking", bookings);
            LOG.info("Booking № " + bookingId + " was deleted");
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
