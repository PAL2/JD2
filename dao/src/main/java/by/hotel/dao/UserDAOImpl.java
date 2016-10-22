package by.hotel.dao;

import by.hotel.connect.DBUtil;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.UserEntity;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements AbstractDAO<UserEntity> {
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

    public UserEntity logIn(String login, String password) throws DaoException {
        UserEntity user;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM UserEntity WHERE login= :login AND password = :password");
            query.setParameter("login", login);
            query.setParameter("password", hash(password));
            user = (UserEntity) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Unable to login");
            throw new DaoException();
        }
        return user;
    }

    public void register(String firstName, String lastName, String login, String password) throws DaoException {
        try {
            Session session = util.getSession();
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(firstName);
            userEntity.setLastName(lastName);
            userEntity.setUserRole("client");
            userEntity.setLogin(login);
            userEntity.setPassword(password);
            session.save(userEntity);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Could not register");
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
    public void create(UserEntity entity) throws DaoException {

    }

    @Override
    public void update(UserEntity entity) throws DaoException {

    }

    @Override
    public void delete(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "DELETE FROM user WHERE user_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to delete the client");
            throw new DaoException();
        }

    }

    public List<UserEntity> getAll() throws DaoException {
        List<UserEntity> allUsers = null;
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(UserEntity.class);
            criteria.add(Restrictions.eq("userRole", "client"));
            allUsers = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Unable to create the list of clients");
            throw new DaoException();
        }
        return allUsers;
    }
}