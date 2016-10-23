package by.hotel.dao;

import by.hotel.connect.DBUtil;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.Account;
import by.hotel.entity.AccountEntity;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AbstractDAO<AccountEntity> {
    private static AccountDAOImpl instance;
    private final Logger LOG = Logger.getLogger(AccountDAOImpl.class);

    private AccountDAOImpl() {
    }

    public static synchronized AccountDAOImpl getInstance() {
        if (instance == null) {
            instance = new AccountDAOImpl();
        }
        return instance;
    }

    public void addAccount(int bookingId) throws DaoException, SQLException {
        Connection conn = DBUtil.getConnection();
        try {
            String query = "INSERT INTO account (account_id, summa) VALUES (NULL, (SELECT (end_date-start_date)*price as duration FROM booking "
                    + "JOIN room USING (room_id) WHERE booking_id=?))";
            conn.setAutoCommit(false);
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            ps.executeUpdate();
            ps.close();
            query = "UPDATE booking SET account_id=LAST_INSERT_ID() WHERE booking_id=?";
            ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            ps.executeUpdate();
            conn.commit();
            ps.close();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            LOG.info("Unable to add account");
            throw new DaoException();
        }
    }

    public List<AccountEntity> getAllAccountByUser(int userId) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<AccountEntity> accounts;
        try {
            String query = "SELECT account.account_id, summa FROM account JOIN booking "
                    + "ON account.account_id=booking.account_id WHERE booking.user_id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            accounts = resultSetToAccountsList(resultSet);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to create a list of accounts");
            throw new DaoException();
        }
        return accounts;
    }

    private List<AccountEntity> resultSetToAccountsList(ResultSet resultSet) throws SQLException {
        List<AccountEntity> accounts = new ArrayList<>();
        while (resultSet.next()) {
            AccountEntity account = new AccountEntity();
            account.setAccountId(resultSet.getInt(1));
            account.setSumma(resultSet.getInt(2));
            accounts.add(account);
        }
        return accounts;
    }

    public void create(Account entity) {
        // TODO Auto-generated method stub
    }

    public void update(Account entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void create(AccountEntity entity) throws DaoException {

    }

    @Override
    public void update(AccountEntity entity) throws DaoException {

    }

    public void delete(int id) {
        // TODO Auto-generated method stub
    }

    public List<AccountEntity> getAll() throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<AccountEntity> accounts;
        try {
            String query = "SELECT account.account_id, summa FROM account JOIN booking "
                    + "ON account.account_id=booking.account_id";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            accounts = resultSetToAccountsList(resultSet);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("Unable to create a list of accounts");
            throw new DaoException();
        }
        return accounts;
    }
}