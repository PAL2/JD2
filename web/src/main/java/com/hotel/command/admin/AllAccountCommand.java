package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.impl.AccountServiceImpl;
import com.hotel.service.impl.BookingServiceImpl;
import com.hotel.service.impl.UserServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.allAccounts");
            List<Booking> bookings = BookingServiceImpl.getInstance().getAllBookingWithAccount();
            request.setAttribute("allBookings", bookings);
            List<Account> accounts = AccountServiceImpl.getInstance().getAll();
            request.setAttribute("allAccounts", accounts);
            List<User> users = UserServiceImpl.getInstance().getAll();
            request.setAttribute("allUsers", users);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
