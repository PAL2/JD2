package by.hotel.service;

import by.hotel.connect.DBUtil;
import by.hotel.dao.AccountDAOImpl;
import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.AccountEntity;
import by.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accounts = accountDAO.getAll();
            transaction.commit();
            LOG.info(accounts);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());

        }
        return accounts;
    }

    public List<AccountEntity> getAllAccountByUser(int userId) throws SQLException, ServiceException {
        List<AccountEntity> accounts;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accounts = accountDAO.getAllAccountByUser(userId);
            transaction.commit();
            LOG.info(accounts);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.info("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return accounts;
    }
}