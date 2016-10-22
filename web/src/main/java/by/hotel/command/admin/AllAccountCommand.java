package by.hotel.command.admin;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.entity.Account;
import by.hotel.entity.Booking;
import by.hotel.entity.UserEntity;
import by.hotel.service.AccountServiceImpl;
import by.hotel.service.BookingServiceImpl;
import by.hotel.service.UserServiceImpl;
import by.hotel.service.exceptions.ServiceException;

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
            List<Account> accounts = AccountServiceImpl.getInstance().getAll();
            request.setAttribute("allAccounts", accounts);
            List<UserEntity> users = UserServiceImpl.getInstance().getAll();
            request.setAttribute("allUsers", users);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
