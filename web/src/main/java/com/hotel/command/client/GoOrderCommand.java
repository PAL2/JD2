package com.hotel.command.client;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class GoOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.order");
        return page;
    }

}
