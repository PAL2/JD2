package com.hotel.service;

import com.hotel.dao.RoomDAOImpl;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */
public class RoomServiceImpl extends AbstractService {
    private static RoomServiceImpl instance;
    private RoomDAOImpl roomDAO = RoomDAOImpl.getInstance();
    final Logger LOG = Logger.getLogger(RoomServiceImpl.class);

    public RoomServiceImpl() {
    }

    public static synchronized RoomServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomServiceImpl();
        }
        return instance;
    }

    public List<Room> getAll(int recordsPerPage, int currentPage) throws SQLException, ServiceException {
        List<Room> rooms;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            rooms = roomDAO.getAll(recordsPerPage, currentPage);
            transaction.commit();
            LOG.info(rooms);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }

    public List<Room> getAvailableRooms(int bookingId) throws SQLException, ServiceException {
        Booking booking;
        List<Room> rooms;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            booking = (Booking) session.get(Booking.class, bookingId);
            rooms = roomDAO.getAvailableRooms(booking);
            transaction.commit();
            LOG.info(booking);
            LOG.info(rooms);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }

    public int getNumberOfPages(int recordsPerPage) throws ServiceException{
        int numberOfPages;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Long numberOfRecords = roomDAO.getAmount();
            numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
            transaction.commit();
            LOG.info(numberOfPages);
        }
        catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return numberOfPages;
    }
}