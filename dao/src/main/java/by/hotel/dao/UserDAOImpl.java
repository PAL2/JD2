package by.hotel.dao;

import by.hotel.connect.DBUtil;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.User;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements AbstractDAO<User> {
    private static UserDAOImpl instance;
    private final Logger LOG = Logger.getLogger(UserDAOImpl.class);

    private UserDAOImpl() {
    }

//    public static UserDAOImpl getInstance() {
//        UserDAOImpl localInstance = instance;
//        if (localInstance == null) {
//            synchronized (UserDAOImpl.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new UserDAOImpl();
//                }
//            }
//        }
//        return localInstance;
//    }

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public User logIn(String login, String password) throws DaoException {
        Connection conn = DBUtil.getConnection();
        User user = new User(login, password);
        try {
            String query = "SELECT user_id, first_name, last_name, user_role FROM user WHERE login=? AND password=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, hash(password));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUserRole(resultSet.getString("user_role"));
            }
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to login");
            throw new DaoException();
        }
        return user;
    }

    public void register(String firstName, String lastName, String login, String password) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "INSERT INTO user (first_name, last_name, user_role, login, password) VALUES (?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, "client");
            ps.setString(4, login);
            ps.setString(5, hash(password));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
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
    public void create(User user) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "INSERT INTO user (first_name, last_name, login, password) VALUES (?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            user.setUserRole("client");
            user.setUserId(0);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOG.info("Failed to create client");
            e.printStackTrace();
            throw new DaoException();
        }
    }

    @Override
    public void update(User user) throws DaoException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "UPDATE user SET first_name=?, last_name=? WHERE user_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getUserId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Failed to update the client");
            throw new DaoException();
        }
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

    public List<User> getAll() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<User> allUsers = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE user_role=\"client\"");
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setUserRole(resultSet.getString(4));
                user.setLogin(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                allUsers.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to create the list of clients");
            throw new DaoException();
        }
        return allUsers;
    }
}