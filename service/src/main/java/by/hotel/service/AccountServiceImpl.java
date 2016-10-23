package by.hotel.service;

import by.hotel.connect.DBUtil;
import by.hotel.dao.AccountDAOImpl;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.AccountEntity;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 02.10.2016.
 */
public class AccountServiceImpl extends AbstractService {
    final Logger LOG = Logger.getLogger(AccountServiceImpl.class);
    private AccountDAOImpl accountDAO = AccountDAOImpl.getInstance();

    private static AccountServiceImpl instance;

    public AccountServiceImpl() {
    }

    public static synchronized AccountServiceImpl getInstance() {
        if (instance == null) {
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    public List<AccountEntity> getAll() throws SQLException, ServiceException {
        List<AccountEntity> accounts;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            accounts = accountDAO.getAll();
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());

        }
        return accounts;
    }

    public List<AccountEntity> getAllAccountByUser(int userId) throws SQLException, ServiceException {
        List<AccountEntity> accounts;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            accounts = accountDAO.getAllAccountByUser(userId);
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return accounts;
    }
}