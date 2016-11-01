package com.hotel.service;

import com.hotel.entity.Room;
import com.hotel.service.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface RoomService extends Service<Room> {
    List<Room> getAll(int recordsPerPage, int currentPage) throws ServiceException;
    List<Room> getAvailableRooms(int bookingId) throws ServiceException;
    int getNumberOfPages(int recordsPerPage) throws ServiceException;
}
