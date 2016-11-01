package com.hotel.command.user;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.service.UserServiceImpl;
import com.hotel.service.exceptions.ServiceException;
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
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
