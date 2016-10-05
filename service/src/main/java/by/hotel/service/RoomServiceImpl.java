package by.hotel.service;

import by.hotel.connect.DBUtil;
import by.hotel.dao.RoomDAOImpl;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.Room;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */
public class RoomServiceImpl extends AbstractService {
    private static RoomServiceImpl instance;
    final Logger LOG = Logger.getLogger(RoomServiceImpl.class);

    public RoomServiceImpl() {
    }

//    public static RoomServiceImpl getInstance() {
//        RoomServiceImpl localInstance = instance;
//        if (localInstance == null) {
//            synchronized (RoomServiceImpl.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new RoomServiceImpl();
//                }
//            }
//        }
//        return localInstance;
//    }

    public static synchronized RoomServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomServiceImpl();
        }
        return instance;
    }

    public List<Room> getAll() throws SQLException, ServiceException {
        List<Room> rooms;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            rooms = RoomDAOImpl.getInstance().getAll();
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }

        return rooms;
    }

    public List<Room> getAvailableRooms(int bookingId) throws SQLException, ServiceException {
        List<Room> rooms;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            rooms = RoomDAOImpl.getInstance().getAvailableRooms(bookingId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }
}