package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface AccountDAO extends DAO<Account> {
    void addAccount (int summa, Booking entity) throws DaoException;
    List<Account> getAllAccountByUser(int userId) throws DaoException;
}
