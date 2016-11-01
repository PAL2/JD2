package com.hotel.service;

import com.hotel.entity.User;
import com.hotel.service.exceptions.ServiceException;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface UserService extends Service<User> {
    User logIn(String login, String password) throws ServiceException;

    void register(String firstName, String lastName, String login, String password) throws ServiceException;
}
