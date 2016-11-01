package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.User;
import com.hotel.service.UserServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class AllUserCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.allUsers");
            List<User> users = UserServiceImpl.getInstance().getAll();
            request.setAttribute("allUsers", users);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
