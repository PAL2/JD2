package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserDAOImpl implements AbstractDAO<User> {
    private static UserDAOImpl instance;
    private final Logger LOG = Logger.getLogger(UserDAOImpl.class);

    private UserDAOImpl() {
    }

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public User logIn(String login, String password) throws DaoException {
        User user;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM User WHERE login= :login AND password = :password");
            query.setParameter("login", login);
            query.setParameter("password", hash(password));
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Unable to login. Error in DAO");
            throw new DaoException();
        }
        return user;
    }

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
            LOG.info("Could not register. Error in DAO");
            throw new DaoException();
        }
    }

    private String hash(String input) {
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

    @Override
    public void create(User entity) throws DaoException {
    }

    @Override
    public void update(User entity) throws DaoException {
    }

    @Override
    public void delete(int userId) throws DaoException {
    }

    public List<User> getAll() throws DaoException {
        List<User> allUsers;
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("userRole", "client"));
            allUsers = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Unable to create the list of clients. Error in DAO");
            throw new DaoException();
        }
        return allUsers;
    }
}