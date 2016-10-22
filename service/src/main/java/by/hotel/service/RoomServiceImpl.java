package by.hotel.service;

import by.hotel.dao.RoomDAOImpl;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.RoomEntity;
import by.hotel.service.exceptions.ServiceException;
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

    public List<RoomEntity> getAll() throws SQLException, ServiceException {
        List<RoomEntity> rooms;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            rooms = roomDAO.getAll();
            transaction.commit();
            LOG.info(rooms);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }

    public List<RoomEntity> getAvailableRooms(int bookingId) throws SQLException, ServiceException {
        List<RoomEntity> rooms;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            rooms = roomDAO.getAvailableRooms(bookingId);
            transaction.commit();
            LOG.info(rooms);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }
}