package com.hotel.dao;

import com.hotel.connect.DBUtil;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.BookingEntity;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements AbstractDAO<BookingEntity> {
    private static BookingDAOImpl instance;

    private final Logger LOG = Logger.getLogger(BookingDAOImpl.class);

    private BookingDAOImpl() {
    }

    public static synchronized BookingDAOImpl getInstance() {
        if (instance == null) {
            instance = new BookingDAOImpl();
        }
        return instance;
    }

    public void addBooking(int userId, int place, String category, LocalDate startDate, LocalDate endDate)
            throws DaoException {
        Connection conn = DBUtil.getConnection();
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);
        try {
            String query = "INSERT INTO booking (user_id, place, category, start_date, end_date, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, place);
            ps.setString(3, category);
            ps.setDate(4, sqlStartDate);
            ps.setDate(5, sqlEndDate);
            ps.setString(6, "new");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to add a booking");
            throw new DaoException();
        }
    }

    private List<BookingEntity> resultSetToBookingsList(ResultSet resultSet) throws SQLException {
        List<BookingEntity> bookings = new ArrayList<>();
        while (resultSet.next()) {
            BookingEntity booking = new BookingEntity();
            booking.setBookingId(resultSet.getInt(1));
            booking.setStartDate(resultSet.getDate(2));
            booking.setEndDate(resultSet.getDate(3));
            booking.setPlace(resultSet.getInt(4));
            booking.setCategory(resultSet.getString(5));
            booking.setRoomId(resultSet.getInt(6));
            booking.setUserId(resultSet.getInt(7));
            booking.setAccountId(resultSet.getInt(8));
            booking.setStatus(resultSet.getString(9));
            bookings.add(booking);
        }
        return bookings;
    }

    public List<BookingEntity> getAllNewBooking() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<BookingEntity> bookings;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM booking WHERE status=\"new\"");
            bookings = resultSetToBookingsList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of bookings");
            throw new DaoException();
        }
        return bookings;
    }

    public void chooseRoom(int bookingId, int roomId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE BookingEntity B SET B.roomId=:roomId, B.status=:status " +
                    "WHERE B.bookingId=:bookingId");
            query.setParameter("roomId", roomId);
            query.setParameter("status", "billed");
            query.setParameter("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Failed to assign the appropriate booking number");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAllBookingByUser(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<BookingEntity> bookings;
        try {
            String query = "SELECT * FROM booking WHERE user_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            bookings = resultSetToBookingsList(resultSet);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list bookings");
            throw new DaoException();
        }
        return bookings;
    }

    public void rejectBooking(int bookingId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "UPDATE booking SET status=? WHERE booking_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, "rejected");
            ps.setInt(2, bookingId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to reject the book");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAllBookingWithAccount() throws DaoException {
        List<BookingEntity> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM BookingEntity B WHERE B.accountId!=0");
            bookings = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of bookings with the invoice. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    public List<BookingEntity> getAllBookingWithAccountByUser(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<BookingEntity> bookings;
        try {
            String query = "SELECT * FROM booking WHERE account_id!=0 AND status=\"billed\" AND user_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            bookings = resultSetToBookingsList(resultSet);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of bookings with the invoice for the customer");
            throw new DaoException();
        }
        return bookings;
    }

    public void payBooking(int bookingId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "UPDATE booking SET status=? WHERE booking_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, "paid");
            ps.setInt(2, bookingId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to pay for a booking");
            throw new DaoException();
        }
    }

    public void refuseBooking(int bookingId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "UPDATE booking SET status=? WHERE booking_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, "refused");
            ps.setInt(2, bookingId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("The client was unable to refuse the book");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAllBookingWithFinishedAccount(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<BookingEntity> bookings;
        try {
            String query = "SELECT * FROM booking WHERE account_id!=0 AND (status=\"paid\" OR status=\"refused\") AND user_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            bookings = resultSetToBookingsList(resultSet);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of fully processed bookings");
            throw new DaoException();
        }
        return bookings;
    }

    @Override
    public void create(BookingEntity entity) throws DaoException {

    }

    @Override
    public void update(BookingEntity entity) throws DaoException {

    }

    @Override
    public void delete(int bookingId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "DELETE FROM booking WHERE booking_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to delete the book");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAll() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<BookingEntity> bookings;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM booking");
            bookings = resultSetToBookingsList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of bookings");
            throw new DaoException();
        }
        return bookings;
    }
}