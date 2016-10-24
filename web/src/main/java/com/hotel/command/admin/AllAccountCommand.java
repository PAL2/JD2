package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.AccountServiceImpl;
import com.hotel.service.BookingServiceImpl;
import com.hotel.service.UserServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class AllAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.allAccounts");
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllBookingWithAccount();
            request.setAttribute("allBookings", bookings);
            List<AccountEntity> accounts = AccountServiceImpl.getInstance().getAll();
            request.setAttribute("allAccounts", accounts);
            List<User> users = UserServiceImpl.getInstance().getAll();
            request.setAttribute("allUsers", users);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
