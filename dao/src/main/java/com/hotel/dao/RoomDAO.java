package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface RoomDAO extends DAO<Room> {
    List<Room> resultSetToRoomsList(ResultSet resultSet) throws SQLException;
    List<Room> getAvailableRooms(Booking booking) throws DaoException;
    List<Room> getAll(int recordsPerPage, int currentPage) throws DaoException;
    Long getAmount() throws DaoException;
}
