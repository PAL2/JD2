package com.hotel.service;

import com.hotel.entity.Account;
import com.hotel.service.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface AccountService extends Service<Account> {
    List<Account> getAllAccountByUser(int userId) throws ServiceException;

}
