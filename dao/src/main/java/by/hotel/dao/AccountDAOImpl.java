package by.hotel.dao;

import by.hotel.connect.DBUtil;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.AccountEntity;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
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
        List<AccountEntity> accounts;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM AccountEntity WHERE bookingEntity.userId=?");
            query.setParameter(0, userId);
            accounts = query.list();
            LOG.info(accounts);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to create a list of accounts");
            throw new DaoException();
        }
        return accounts;
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
        List<AccountEntity> accounts;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM AccountEntity");
            accounts = query.list();
            LOG.info(accounts);
        } catch (HibernateException e) {
            LOG.error("Unable to create a list of accounts. Error in DAO");
            throw new DaoException();
        }
        return accounts;
    }
}