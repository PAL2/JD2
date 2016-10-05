package by.hotel.command.user;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.service.UserServiceImpl;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(RegCommand.class);
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            UserServiceImpl.getInstance().register(firstName, lastName, login, password);
            request.setAttribute("regSuccess", MessageManager.getProperty("message.regSuccess"));
            page = ConfigurationManager.getProperty("path.page.login");
            LOG.info("Register a new user with the login " + login + ", name and surname: " +
                    firstName + " " + lastName);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
