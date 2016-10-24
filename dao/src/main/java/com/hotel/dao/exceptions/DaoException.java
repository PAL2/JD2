package com.hotel.dao.exceptions;

/**
 * Created by Алексей on 02.10.2016.
 */
public class DaoException extends Exception {

    public DaoException(){};

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable cause){
        super(message, cause);
    }

}
