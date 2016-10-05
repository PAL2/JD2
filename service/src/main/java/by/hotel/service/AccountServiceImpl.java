package by.hotel.service;

import by.hotel.connect.DBUtil;
import by.hotel.dao.AccountDAOImpl;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.Account;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 02.10.2016.
 */
public class AccountServiceImpl extends AbstractService {
    final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    private static AccountServiceImpl instance;

    public AccountServiceImpl() {
    }

//    public static AccountServiceImpl getInstance() {
//        AccountServiceImpl localInstance = instance;
//        if (localInstance == null) {
//            synchronized (AccountServiceImpl.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new AccountServiceImpl();
//                }
//            }
//        }
//        return localInstance;
//    }

    public static synchronized AccountServiceImpl getInstance() {
        if (instance == null) {
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    public List<Account> getAll() throws SQLException, ServiceException {
        List<Account> accounts;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            accounts = AccountDAOImpl.getInstance().getAll();
            conn.commit();
            LOG.info("Transaction is completed successfully");
        } catch (SQLException | DaoException e) {
            conn.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());

        }
        return accounts;
    }

    public List<Account> getAllAccountByUser(int userId) throws SQLException, ServiceException {
        List<Account> accounts;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            accounts = AccountDAOImpl.getInstance().getAllAccountByUser(userId);
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
