package com.hotel.service;

import com.hotel.connect.DBUtil;
import com.hotel.dao.AccountDAOImpl;
import com.hotel.dao.BookingDAOImpl;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.BookingEntity;
import com.hotel.entity.Room;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */
public class BookingServiceImpl extends AbstractService {
    private static BookingServiceImpl instance;
    private BookingDAOImpl bookingDAO = BookingDAOImpl.getInstance();
    private AccountDAOImpl accountDAO = AccountDAOImpl.getInstance();
    final Logger LOG = Logger.getLogger(BookingServiceImpl.class);

    public BookingServiceImpl() {
    }

    public static synchronized BookingServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookingServiceImpl();
        }
        return instance;
    }

    public List<Booking> getAllBookingWithAccount() throws SQLException, ServiceException {
        List<Booking> bookings;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookings = bookingDAO.getAllBookingWithAccount();
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public List<Booking> getAll() throws SQLException, ServiceException {
        List<Booking> bookings;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookings = bookingDAO.getAll();
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;

    }

    public void chooseRoom(int bookingId, int roomId) throws SQLException, ServiceException {
        BookingEntity booking;
        Room room;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.chooseRoom(bookingId, roomId);
            booking = (BookingEntity) session.get(BookingEntity.class, bookingId);
            room = (Room) session.get(Room.class, booking.getRoomId());
            Date startDate = booking.getStartDate();
            Date endDate = booking.getEndDate();
            long st = startDate.getTime();
            long en = endDate.getTime();
            int summa = (int) ((en - st) / 24 / 60 / 60 / 1000) * room.getPrice();
            accountDAO.addAccount(summa, booking);
            transaction.commit();
            LOG.info(booking);
            LOG.info(room);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void delete(int bookingId) throws SQLException, ServiceException {
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookingDAO.delete(bookingId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllNewBooking() throws SQLException, ServiceException {
        List<Booking> bookings;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookings = bookingDAO.getAllNewBooking();
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void rejectBooking(int bookingId) throws SQLException, ServiceException {
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookingDAO.rejectBooking(bookingId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws SQLException, ServiceException {
        List<Booking> bookings;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookings = bookingDAO.getAllBookingWithFinishedAccount(userId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public List<Booking> getAllBookingByUser(int userId) throws SQLException, ServiceException {
        List<Booking> bookings;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookings = bookingDAO.getAllBookingByUser(userId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void addBooking(LocalDate startDate, LocalDate endDate, int userId, int place, String category)
            throws SQLException, ServiceException {
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookingDAO.addBooking(userId, place, category, startDate, endDate);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void payBooking(int bookingId) throws SQLException, ServiceException {
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookingDAO.payBooking(bookingId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void refuseBooking(int bookingId) throws SQLException, ServiceException {
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookingDAO.refuseBooking(bookingId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookingWithAccountByUser(int userId) throws SQLException, ServiceException {
        List<Booking> bookings;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            bookings = bookingDAO.getAllBookingWithAccountByUser(userId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }
}