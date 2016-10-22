package by.hotel.command.admin;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.entity.RoomEntity;
import by.hotel.service.RoomServiceImpl;
import by.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class AllRoomCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.allRooms");
            List<RoomEntity> rooms = RoomServiceImpl.getInstance().getAll();
            request.setAttribute("allRooms", rooms);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
