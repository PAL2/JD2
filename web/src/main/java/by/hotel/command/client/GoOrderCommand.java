package by.hotel.command.client;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class GoOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.order");
        return page;
    }

}
