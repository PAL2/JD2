package com.hotel.command.client;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Account;
import com.hotel.entity.BookingEntity;
import com.hotel.entity.User;
import com.hotel.service.AccountServiceImpl;
import com.hotel.service.BookingServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class UnpaidAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            List<BookingEntity> bookings = BookingServiceImpl.getInstance().getAllBookingWithAccountByUser(userId);
            request.setAttribute("bookingByUser", bookings);
            List<Account> accounts = AccountServiceImpl.getInstance().getAllAccountByUser(userId);
            request.setAttribute("accountById", accounts);
            page = ConfigurationManager.getProperty("path.page.unpaidAccounts");
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
