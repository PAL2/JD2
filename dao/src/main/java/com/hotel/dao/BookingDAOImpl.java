package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.BookingEntity;
import com.hotel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class BookingDAOImpl implements AbstractDAO<BookingEntity> {
    private static BookingDAOImpl instance;

    private final Logger LOG = Logger.getLogger(BookingDAOImpl.class);

    private BookingDAOImpl() {
    }

    public static synchronized BookingDAOImpl getInstance() {
        if (instance == null) {
            instance = new BookingDAOImpl();
        }
        return instance;
    }

    public void addBooking(int userId, int place, String category, LocalDate startDate, LocalDate endDate)
            throws DaoException {
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);
        try {
            Session session = util.getSession();
            User user = (User) session.get(User.class, userId);
            BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setUserId(userId);
            bookingEntity.setPlace(place);
            bookingEntity.setCategory(category);
            bookingEntity.setStartDate(sqlStartDate);
            bookingEntity.setEndDate(sqlEndDate);
            bookingEntity.setStatus("new");
            bookingEntity.setUser(user);
            session.save(bookingEntity);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Unable to add a booking. Error in DAO");
            throw new DaoException();
        }
    }
    
    public List<BookingEntity> getAllNewBooking() throws DaoException {
        List<BookingEntity> bookings;
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(BookingEntity.class);
            criteria.add(Restrictions.eq("status", "new"));
            bookings = criteria.list();
            LOG.info(bookings);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Failed to create a list of bookings. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    public void chooseRoom(int bookingId, int roomId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE BookingEntity B SET B.roomId=:roomId, B.status=:status " +
                    "WHERE B.bookingId=:bookingId");
            query.setParameter("roomId", roomId);
            query.setParameter("status", "billed");
            query.setParameter("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Failed to assign the appropriate booking number");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAllBookingByUser(int userId) throws DaoException {
        List<BookingEntity> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM BookingEntity B WHERE B.userId=?");
            query.setParameter(0, userId);
            bookings = query.list();
            LOG.info(bookings);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list bookings. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    public void rejectBooking(int bookingId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE BookingEntity B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setParameter("status", "rejected");
            query.setParameter("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Unable to reject the book. Error in DAO");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAllBookingWithAccount() throws DaoException {
        List<BookingEntity> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM BookingEntity B WHERE B.accountId!=0");
            bookings = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of bookings with the invoice. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    public List<BookingEntity> getAllBookingWithAccountByUser(int userId) throws DaoException {
        List<BookingEntity> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM BookingEntity B " +
                    "WHERE B.accountId!=0 AND B.status=:status AND B.userId=:userId");
            query.setParameter("status", "billed");
            query.setParameter("userId", userId);
            bookings = query.list();
            LOG.info(bookings);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Failed to create a list of bookings with the invoice for the customer. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    public void payBooking(int bookingId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE BookingEntity B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setString("status", "paid");
            query.setInteger("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to pay for a booking. Error in DAO");
            throw new DaoException();
        }
    }

    public void refuseBooking(int bookingId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE BookingEntity B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setString("status", "refused");
            query.setInteger("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("The client was unable to refuse the book");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAllBookingWithFinishedAccount(int userId) throws DaoException {
        List<BookingEntity> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM BookingEntity B " +
                    "WHERE B.accountId!=0 AND (B.status=? OR B.status=?) AND B.userId=?");
            query.setParameter(0, "paid");
            query.setParameter(1, "refused");
            query.setParameter(2, userId);
            bookings = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of fully processed bookings. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    @Override
    public void create(BookingEntity entity) throws DaoException {

    }

    @Override
    public void update(BookingEntity entity) throws DaoException {

    }

    @Override
    public void delete(int bookingId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("DELETE FROM BookingEntity B WHERE B.bookingId=?");
            query.setParameter(0, bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Unable to delete the book. Error in DAO");
            throw new DaoException();
        }
    }

    public List<BookingEntity> getAll() throws DaoException {
        List<BookingEntity> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM BookingEntity");
            bookings = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.info("Failed to create a list of bookings. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }
}