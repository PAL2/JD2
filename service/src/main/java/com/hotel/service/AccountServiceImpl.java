package com.hotel.service;

import com.hotel.dao.AccountDAOImpl;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Account;
import com.hotel.service.exceptions.ServiceException;
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

    public List<Account> getAll() throws SQLException, ServiceException {
        List<Account> accounts;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accounts = accountDAO.getAll();
            transaction.commit();
            LOG.info(accounts);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed. Error in service");
            throw new ServiceException(e.getMessage());

        }
        return accounts;
    }

    public List<Account> getAllAccountByUser(int userId) throws SQLException, ServiceException {
        List<Account> accounts;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accounts = accountDAO.getAllAccountByUser(userId);
            transaction.commit();
            LOG.info(accounts);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return accounts;
    }
}