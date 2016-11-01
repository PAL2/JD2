package com.hotel.service;

import com.hotel.dao.impl.AccountDAOImpl;
import com.hotel.dao.impl.BookingDAOImpl;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
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

    public List<Booking> getAllBookingWithAccount() throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingWithAccount();
            transaction.commit();
            LOG.info(bookings);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public List<Booking> getAll() throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAll();
            transaction.commit();
            LOG.info(bookings);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;

    }

    public void chooseRoom(int bookingId, int roomId) throws ServiceException {
        Booking booking;
        Room room;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.chooseRoom(bookingId, roomId);
            booking = (Booking) session.get(Booking.class, bookingId);
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
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void delete(int bookingId) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.delete(bookingId);
            transaction.commit();
            LOG.info("Deleting is completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllNewBooking() throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllNewBooking();
            transaction.commit();
            LOG.info(bookings);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void rejectBooking(int bookingId) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.rejectBooking(bookingId);
            transaction.commit();
            LOG.info("Rejecting is completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingWithFinishedAccount(userId);
            transaction.commit();
            LOG.info(bookings);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public List<Booking> getAllBookingByUser(int userId) throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingByUser(userId);
            transaction.commit();
            LOG.info(bookings);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void addBooking(LocalDate startDate, LocalDate endDate, int userId, int place, String category)
            throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.addBooking(userId, place, category, startDate, endDate);
            transaction.commit();
            LOG.info("Booking added is successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void payBooking(int bookingId) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.payBooking(bookingId);
            transaction.commit();
            LOG.info("Booking is paid successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void refuseBooking(int bookingId) throws SQLException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.refuseBooking(bookingId);
            transaction.commit();
            LOG.info("Transaction is completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookingWithAccountByUser(int userId) throws SQLException, ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingWithAccountByUser(userId);
            transaction.commit();
            LOG.info(bookings);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void save(Booking booking) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.save(booking);
            transaction.commit();
            LOG.info("Transaction is completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }
}