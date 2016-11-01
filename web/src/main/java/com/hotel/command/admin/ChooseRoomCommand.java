package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Room;
import com.hotel.service.impl.RoomServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
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
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
