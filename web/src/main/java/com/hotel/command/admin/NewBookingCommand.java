package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Booking;
import com.hotel.service.impl.BookingServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class NewBookingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.admin");
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllNewBooking();
            request.setAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
