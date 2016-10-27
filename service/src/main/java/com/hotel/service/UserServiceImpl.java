package com.hotel.service;

import com.hotel.dao.UserDAOImpl;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.User;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */
public class UserServiceImpl extends AbstractService {
    private static UserServiceImpl instance;
    private UserDAOImpl userDAO = UserDAOImpl.getInstance();
    final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public User logIn(String login, String password) throws ServiceException {
        User user;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            user = userDAO.logIn(login, password);
            transaction.commit();
            LOG.info(user);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return user;
    }

    public List<User> getAll() throws SQLException, ServiceException {
        List<User> users;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            users = userDAO.getAll();
            transaction.commit();
            LOG.info(users);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed. Error in Service");
            throw new ServiceException(e.getMessage());
        }
        return users;
    }

    public void register(String firstName, String lastName, String login, String password)
            throws SQLException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.register(firstName, lastName, login, password);
            transaction.commit();
            LOG.info("Transaction is completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void save(User user) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.save(user);
            transaction.commit();
            LOG.info("Transaction is completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void delete(int id) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.delete(id);
            transaction.commit();
            LOG.info("Transaction is completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }
}