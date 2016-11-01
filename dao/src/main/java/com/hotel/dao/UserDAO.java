package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.User;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface UserDAO extends DAO<User> {
    User logIn(String login, String password) throws DaoException;

    void register(String firstName, String lastName, String login, String password) throws DaoException;

    String hash(String input);
}
