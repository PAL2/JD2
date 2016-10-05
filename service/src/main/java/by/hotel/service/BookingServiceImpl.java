package by.hotel.service;

import by.hotel.connect.DBUtil;
import by.hotel.dao.AccountDAOImpl;
import by.hotel.dao.BookingDAOImpl;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.Booking;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */
public class BookingServiceImpl extends AbstractService {
    private static BookingServiceImpl instance;
    final Logger LOG = Logger.getLogger(BookingServiceImpl.class);

    public BookingServiceImpl() {
    }

//    public static BookingServiceImpl getInstance() {
//        BookingServiceImpl localInstance = instance;
//        if (localInstance == null) {
//            synchronized (BookingServiceImpl.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new BookingServiceImpl();
//                }
//            }
//        }
//        return localInstance;
//    }

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
            bookings = BookingDAOImpl.getInstance().getAllBookingWithAccount();
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
            bookings = BookingDAOImpl.getInstance().getAll();
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
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            BookingDAOImpl.getInstance().chooseRoom(bookingId, roomId);
            AccountDAOImpl.getInstance().addAccount(bookingId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void delete(int bookingId) throws SQLException, ServiceException {
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            BookingDAOImpl.getInstance().delete(bookingId);
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
            bookings = BookingDAOImpl.getInstance().getAllNewBooking();
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
            BookingDAOImpl.getInstance().rejectBooking(bookingId);
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
            bookings = BookingDAOImpl.getInstance().getAllBookingWithFinishedAccount(userId);
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
            bookings = BookingDAOImpl.getInstance().getAllBookingByUser(userId);
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
            BookingDAOImpl.getInstance().addBooking(userId, place, category, startDate, endDate);
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
            BookingDAOImpl.getInstance().payBooking(bookingId);
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
            BookingDAOImpl.getInstance().refuseBooking(bookingId);
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
            bookings = BookingDAOImpl.getInstance().getAllBookingWithAccountByUser(userId);
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
