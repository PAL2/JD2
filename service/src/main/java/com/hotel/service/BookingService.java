package com.hotel.service;

import com.hotel.entity.Booking;
import com.hotel.service.exceptions.ServiceException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface BookingService extends Service<Booking> {
    List<Booking> getAllBookingWithAccount() throws ServiceException;
    void chooseRoom(int bookingId, int roomId) throws ServiceException;
    List<Booking> getAllNewBooking() throws ServiceException;
    void rejectBooking(int bookingId) throws ServiceException;
    List<Booking> getAllBookingWithFinishedAccount(int userId) throws ServiceException;
    List<Booking> getAllBookingByUser(int userId) throws ServiceException;
    void addBooking(LocalDate startDate, LocalDate endDate, int userId, int place, String category)
            throws ServiceException;
    void payBooking(int bookingId) throws ServiceException;
    void refuseBooking(int bookingId) throws ServiceException;
    List<Booking> getAllBookingWithAccountByUser(int userId) throws ServiceException;

}
