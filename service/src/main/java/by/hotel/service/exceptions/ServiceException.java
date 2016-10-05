package by.hotel.service.exceptions;

/**
 * Created by Алексей on 02.10.2016.
 */
public class ServiceException extends Exception {
    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

}
