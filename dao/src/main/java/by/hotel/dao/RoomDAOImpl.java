package by.hotel.dao;

import by.hotel.connect.DBUtil;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.Booking;
import by.hotel.entity.Room;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements AbstractDAO<Room> {
    private static RoomDAOImpl instance;
    private final Logger LOG = Logger.getLogger(RoomDAOImpl.class);

    private RoomDAOImpl() {
    }

//    public static RoomDAOImpl getInstance() {
//        RoomDAOImpl localInstance = instance;
//        if (localInstance == null) {
//            synchronized (RoomDAOImpl.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new RoomDAOImpl();
//                }
//            }
//        }
//        return localInstance;
//    }

    public static synchronized RoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new RoomDAOImpl();
        }
        return instance;
    }

    private List<Room> resultSetToRoomsList(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            Room room = new Room();
            room.setRoomId(resultSet.getInt(1));
            room.setCategoryRoom(resultSet.getString(2));
            room.setPlace(resultSet.getInt(3));
            room.setPrice(resultSet.getInt(4));
            rooms.add(room);
        }
        return rooms;
    }

    public List<Room> getAvailableRooms(int bookingId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Room> rooms;
        Booking booking = getBookingById(bookingId);
        Date startDate = Date.valueOf(booking.getStartDate());
        Date endDate = Date.valueOf(booking.getEndDate());
        try {
            String query = "(SELECT r.room_id, r.category, r.place, r.price FROM room AS r "
                    + "LEFT JOIN booking AS b ON (b.room_id=r.room_id) LEFT JOIN (SELECT room_id FROM booking AS b "
                    + "WHERE (b.start_date BETWEEN ? AND ? OR b.end_date BETWEEN ? AND ?)) AS v "
                    + "ON (v.room_id=b.room_id) WHERE (r.category=?) AND (r.place=?) AND (b.room_id IS NULL))";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.setString(5, booking.getCategory());
            ps.setInt(6, booking.getPlace());
            ResultSet resultSet = ps.executeQuery();
            rooms = resultSetToRoomsList(resultSet);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to create a list of matching numbers");
            throw new DaoException();
        }
        return rooms;
    }

    public Booking getBookingById(int bookingId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        Booking booking = new Booking();
        try {
            String query = "SELECT booking_id, start_date, end_date, place, category, room_id, user_id, account_id, status FROM booking WHERE booking_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                booking.setBookingId(resultSet.getInt(1));
                booking.setStartDate(resultSet.getDate(2).toLocalDate());
                booking.setEndDate(resultSet.getDate(3).toLocalDate());
                booking.setPlace(resultSet.getInt(4));
                booking.setCategoryRoom(resultSet.getString(5));
                booking.setRoomId(resultSet.getInt(6));
                booking.setUserId(resultSet.getInt(7));
                booking.setAccountId(resultSet.getInt(8));
                booking.setStatus(resultSet.getString(9));
            }
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            LOG.info("Failed to create a list bookings");
            e.printStackTrace();
            throw new DaoException();
        }
        return booking;

    }

    public List<Room> getAll() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Room> rooms;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM room");
            rooms = resultSetToRoomsList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to create a list of numbers");
            throw new DaoException();
        }
        return rooms;
    }

    @Override
    public void create(Room entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(Room entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
    }
}