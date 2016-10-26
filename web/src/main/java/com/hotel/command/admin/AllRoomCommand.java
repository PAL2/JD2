package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Room;
import com.hotel.service.RoomServiceImpl;
import com.hotel.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class AllRoomCommand implements ActionCommand {
    private int currentPage;
    private int recordsPerPage;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        System.out.println("page " + page);
        System.out.println("#1 " + request.getParameter("recordsPerPage"));
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
            System.out.println("not0-1 " + recordsPerPage);
        } else {
            recordsPerPage = 6;
        }
        System.out.println("records per page: " + recordsPerPage);
        System.out.println("#2 " + request.getParameter("currentPage"));
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.valueOf(request.getParameter("currentPage"));
            recordsPerPage = (Integer) session.getAttribute("recordsPerPage");
            System.out.println("not0-2 " + currentPage);
            System.out.println("not0-3 " + recordsPerPage);
        } else {
            currentPage = 1;
            System.out.println("else " + recordsPerPage);
        }
        System.out.println("current page:" + currentPage);
        try {
            int numberOfPages = RoomServiceImpl.getInstance().getNumberOfPages(recordsPerPage);
            page = ConfigurationManager.getProperty("path.page.allRooms");
            List<Room> rooms = RoomServiceImpl.getInstance().getAll(recordsPerPage, currentPage);
            session.setAttribute("allRooms", rooms);
            session.setAttribute("numberOfPages", numberOfPages);
            session.setAttribute("currentPage", currentPage);
            session.setAttribute("recordsPerPage", recordsPerPage);
        } catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
