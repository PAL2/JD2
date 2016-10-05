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

public class BillCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(BillCommand.class);
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        int bookingId = (int) request.getSession().getAttribute("booking_id");
        try {
            page = ConfigurationManager.getProperty("path.page.admin");
            BookingServiceImpl.getInstance().chooseRoom(bookingId, roomId);
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllNewBooking();
            request.setAttribute("newBooking", bookings);
            LOG.info("Booking № " + bookingId + " confirmed and sent account");
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
