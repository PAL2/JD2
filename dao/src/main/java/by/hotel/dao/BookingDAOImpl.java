package by.hotel.dao;

import by.hotel.connect.DBUtil;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.Booking;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements AbstractDAO<Booking> {
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

    private List<Booking> resultSetToBookingsList(ResultSet resultSet) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = new Booking();
            booking.setBookingId(resultSet.getInt(1));
            booking.setStartDate(resultSet.getDate(2).toLocalDate());
            booking.setEndDate(resultSet.getDate(3).toLocalDate());
            booking.setPlace(resultSet.getInt(4));
            booking.setCategoryRoom(resultSet.getString(5));
            booking.setRoomId(resultSet.getInt(6));
            booking.setUserId(resultSet.getInt(7));
            booking.setAccountId(resultSet.getInt(8));
            booking.setStatus(resultSet.getString(9));
            bookings.add(booking);
        }
        return bookings;
    }

    public List<Booking> getAllNewBooking() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Booking> bookings;
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
        Connection conn = DBUtil.getConnection();
        try {
            String query = "UPDATE booking SET room_id=?, status=? WHERE booking_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, roomId);
            ps.setString(2, "billed");
            ps.setInt(3, bookingId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to assign the appropriate booking number");
            throw new DaoException();
        }
    }

    public List<Booking> getAllBookingByUser(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Booking> bookings;
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

    public List<Booking> getAllBookingWithAccount() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Booking> bookings;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM booking WHERE account_id!=0");
            bookings = resultSetToBookingsList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of bookings with the invoice");
            throw new DaoException();
        }
        return bookings;
    }

    public List<Booking> getAllBookingWithAccountByUser(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Booking> bookings;
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

    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Booking> bookings;
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
    public void create(Booking entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(Booking entity) {
        // TODO Auto-generated method stub
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

    public List<Booking> getAll() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Booking> bookings;
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