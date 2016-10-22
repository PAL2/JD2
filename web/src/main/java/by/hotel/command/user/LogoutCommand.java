package by.hotel.command.user;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.entity.User;
import by.hotel.entity.UserEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(LogoutCommand.class);
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        String login = user.getLogin();
        LOG.info("Website left user: " + login);
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        return page;
    }

}
