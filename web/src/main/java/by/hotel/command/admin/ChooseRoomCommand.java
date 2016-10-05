package by.hotel.command.admin;

import by.hotel.command.ActionCommand;
import by.hotel.command.ConfigurationManager;
import by.hotel.command.MessageManager;
import by.hotel.entity.Room;
import by.hotel.service.RoomServiceImpl;
import by.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ChooseRoomCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        request.getSession().setAttribute("booking_id", bookingId);
        try {
            page = ConfigurationManager.getProperty("path.page.chooseRoom");
            List<Room> rooms = RoomServiceImpl.getInstance().getAvailableRooms(bookingId);
            request.setAttribute("availableRooms", rooms);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
