package by.hotel.dao;

import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.AccountEntity;
import by.hotel.entity.BookingEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

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

    public void addAccount(int summa, BookingEntity booking) throws DaoException, SQLException {
        AccountEntity account = new AccountEntity();
        try {
            Session session = util.getSession();
            account.setSumma(summa);
            booking.setStatus("billed");
            account.setBookingEntity(booking);
            booking.setAccountEntity(account);
            session.save(booking);
            session.save(account);
            LOG.info(account);
        } catch (HibernateException e) {
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