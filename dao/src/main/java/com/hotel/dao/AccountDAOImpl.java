package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class AccountDAOImpl implements AbstractDAO<Account> {
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

    public void addAccount(int summa, Booking booking) throws DaoException, SQLException {
        Account account = new Account();
        try {
            Session session = util.getSession();
            account.setSumma(summa);
            booking.setStatus("billed");
            account.setBooking(booking);
            booking.setAccount(account);
            session.save(booking);
            session.save(account);
            LOG.info(account);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to add account. Error in DAO");
            throw new DaoException();
        }
    }

    public List<Account> getAllAccountByUser(int userId) throws DaoException {
        List<Account> accounts;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM Account WHERE booking.userId=:userId");
            query.setParameter("userId", userId);
            accounts = query.list();
            LOG.info(accounts);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to create a list of accounts. Error in DAO");
            throw new DaoException();
        }
        return accounts;
    }

    @Override
    public void save(Account entity) throws DaoException {
        try {
            Session session = util.getSession();
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            LOG.error("Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public void update(Account entity) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Session session = util.getSession();
            Account account = (Account) session.get(Account.class, id);
            session.delete(account);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to delete the book. Error in DAO");
            throw new DaoException();
        }
    }

    public List<Account> getAll() throws DaoException {
        List<Account> accounts;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM Account");
            accounts = query.list();
            LOG.info(accounts);
        } catch (HibernateException e) {
            LOG.error("Unable to create a list of accounts. Error in DAO");
            throw new DaoException();
        }
        return accounts;
    }
}