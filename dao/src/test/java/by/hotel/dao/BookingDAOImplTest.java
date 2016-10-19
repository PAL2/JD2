package by.hotel.dao;

import by.hotel.entity.Booking;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Алексей on 06.10.2016.
 */
public class BookingDAOImplTest {
    private Booking expected;

    public void setBooking() {
        //expected = new Booking(1, 2011-02-12, 2012-01-22, 2, "lux", 1, 1, 1, "new");

    }

    @Test
    public void testGetInstance() throws Exception {
        BookingDAOImpl instance1 = BookingDAOImpl.getInstance();
        BookingDAOImpl instance2 = BookingDAOImpl.getInstance();
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testAddBooking() throws Exception {
        //  BookingDAOImpl.getInstance().addBooking(1, 3, "standard", 01-01-2022, 2012-02-02);
    }
}