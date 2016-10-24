package com.hotel.dao;

import com.hotel.connect.DBUtil;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.BookingEntity;
import com.hotel.entity.Room;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements AbstractDAO<Room> {
    private final String GET_ALL_ROOMS = " from Room";
    private static RoomDAOImpl instance;
    private final Logger LOG = Logger.getLogger(RoomDAOImpl.class);

    private RoomDAOImpl() {
    }

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
            room.setCategory(resultSet.getString(2));
            room.setPlace(resultSet.getInt(3));
            room.setPrice(resultSet.getInt(4));
            rooms.add(room);
        }
        return rooms;
    }

    public List<Room> getAvailableRooms(BookingEntity booking) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Room> rooms;
        Date startDate = (Date) booking.getStartDate();
        Date endDate = (Date) booking.getEndDate();
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
            conn.commit();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Unable to create a list of matching numbers");
            throw new DaoException();
        }
        return rooms;
    }


    public List<Room> getAll() throws DaoException {
        List<Room> rooms;
        try {
            Session session = util.getSession();
            Query query = session.createQuery(GET_ALL_ROOMS);
            rooms = query.list();
            LOG.info(rooms);
        } catch (HibernateException e) {
            LOG.error("Unable to return list of clients. Error in DAO: ");
            throw new DaoException();
        }
        return rooms;
    }

    @Override
    public void create(Room entity) throws DaoException {

    }

    @Override
    public void update(Room entity) throws DaoException {

    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
    }
}