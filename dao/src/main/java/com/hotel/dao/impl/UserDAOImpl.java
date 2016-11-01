package com.hotel.dao.impl;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.UserDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
    private static UserDAOImpl instance;
    private final Logger LOG = Logger.getLogger(UserDAOImpl.class);

    private UserDAOImpl() {
        super(User.class);
    }

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public User logIn(String login, String password) throws DaoException {
        User user;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM User WHERE login= :login AND password = :password");
            query.setParameter("login", login);
            query.setParameter("password", hash(password));
            user = (User) query.uniqueResult();
            LOG.info(user);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to login. Error in DAO");
            throw new DaoException();
        }
        return user;
    }

    @Override
    public void register(String firstName, String lastName, String login, String password) throws DaoException {
        try {
            Session session = util.getSession();
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserRole("client");
            user.setLogin(login);
            user.setPassword(hash(password));
            session.save(user);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Could not register. Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public String hash(String input) {
        String md5Hashed = null;
        if (null == input) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5Hashed = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Hashed;
    }
}