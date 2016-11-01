package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Room;
import com.hotel.service.impl.RoomServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllRoomCommand implements ActionCommand {
    private int currentPage;
    private int recordsPerPage;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        recordsPerPage = 10;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.valueOf(request.getParameter("currentPage"));
        } else {
            currentPage = 1;
        }
        try {
            int numberOfPages = RoomServiceImpl.getInstance().getNumberOfPages(recordsPerPage);
            page = ConfigurationManager.getProperty("path.page.allRooms");
            List<Room> rooms = RoomServiceImpl.getInstance().getAll(recordsPerPage, currentPage);
            request.setAttribute("allRooms", rooms);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
