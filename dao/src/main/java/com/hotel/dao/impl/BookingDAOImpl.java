package com.hotel.dao.impl;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.BookingDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
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

public class BookingDAOImpl extends AbstractDAO<Booking> implements BookingDAO {
    private static BookingDAOImpl instance;

    private final Logger LOG = Logger.getLogger(BookingDAOImpl.class);

    private BookingDAOImpl() {
        super(Booking.class);
    }

    public static synchronized BookingDAOImpl getInstance() {
        if (instance == null) {
            instance = new BookingDAOImpl();
        }
        return instance;
    }

    @Override
    public void addBooking(int userId, int place, String category, LocalDate startDate, LocalDate endDate)
            throws DaoException {
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);
        try {
            Session session = util.getSession();
            User user = (User) session.get(User.class, userId);
            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setPlace(place);
            booking.setCategory(category);
            booking.setStartDate(sqlStartDate);
            booking.setEndDate(sqlEndDate);
            booking.setStatus("new");
            booking.setUser(user);
            session.save(booking);
            LOG.info(booking);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to add a booking. Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public List<Booking> getAllNewBooking() throws DaoException {
        List<Booking> bookings;
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(Booking.class);
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

    @Override
    public void chooseRoom(int bookingId, int roomId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE Booking B SET B.roomId=:roomId, B.status=:status " +
                    "WHERE B.bookingId=:bookingId");
            query.setParameter("roomId", roomId);
            query.setParameter("status", "billed");
            query.setParameter("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Failed to assign the appropriate booking number. Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public List<Booking> getAllBookingByUser(int userId) throws DaoException {
        List<Booking> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM Booking B WHERE B.userId=:userId");
            query.setParameter("userId", userId);
            bookings = query.list();
            LOG.info(bookings);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Failed to create a list bookings. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    @Override
    public void rejectBooking(int bookingId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE Booking B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setParameter("status", "rejected");
            query.setParameter("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to reject the book. Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public List<Booking> getAllBookingWithAccount() throws DaoException {
        List<Booking> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM Booking B WHERE B.accountId!=0");
            bookings = query.list();
            LOG.info(bookings);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Failed to create a list of bookings with the invoice. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

    @Override
    public List<Booking> getAllBookingWithAccountByUser(int userId) throws DaoException {
        List<Booking> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM Booking B " +
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

    @Override
    public void payBooking(int bookingId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE Booking B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setString("status", "paid");
            query.setInteger("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to pay for a booking. Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public void refuseBooking(int bookingId) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery("UPDATE Booking B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setString("status", "refused");
            query.setInteger("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("The client was unable to refuse the book. Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws DaoException {
        List<Booking> bookings;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM Booking B " +
                    "WHERE B.accountId!=0 AND (B.status=:paid OR B.status=:refused) AND B.userId=:userId");
            query.setString("paid", "paid");
            query.setString("refused", "refused");
            query.setInteger("userId", userId);
            bookings = query.list();
            LOG.info(bookings);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Failed to create a list of fully processed bookings. Error in DAO");
            throw new DaoException();
        }
        return bookings;
    }

}