package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface BookingDAO extends DAO<Booking> {
    void addBooking(int userId, int place, String category, LocalDate startDate, LocalDate endDate)
            throws DaoException;

    List<Booking> getAllNewBooking() throws DaoException;

    void chooseRoom(int bookingId, int roomId) throws DaoException;

    List<Booking> getAllBookingByUser(int userId) throws DaoException;

    void rejectBooking(int bookingId) throws DaoException;

    List<Booking> getAllBookingWithAccount() throws DaoException;

    List<Booking> getAllBookingWithAccountByUser(int userId) throws DaoException;

    void payBooking(int bookingId) throws DaoException;

    void refuseBooking(int bookingId) throws DaoException;

    List<Booking> getAllBookingWithFinishedAccount(int userId) throws DaoException;
}
