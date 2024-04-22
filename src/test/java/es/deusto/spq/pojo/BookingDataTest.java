package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BookingDataTest {

    private BookingData bookingData;
    private Date mockDate;

    @Before
    public void setUp() {
        bookingData = new BookingData();
        mockDate = mock(Date.class);
    }

    @Test
    public void testSetAndGetId() {
        bookingData.setId(1);
        assertEquals(1, bookingData.getId());
    }

    @Test
    public void testSetAndGetRoom() {
        bookingData.setRoom(101);
        assertEquals(101, bookingData.getRoom());
    }

    @Test
    public void testSetAndGetCustomer() {
        bookingData.setCustomer("customer@example.com");
        assertEquals("customer@example.com", bookingData.getCustomer());
    }

    @Test
    public void testSetAndGetCheckIn() {
        bookingData.setCheckIn(mockDate);
        assertEquals(mockDate, bookingData.getCheckIn());
    }

    @Test
    public void testSetAndGetDays() {
        bookingData.setDays(3);
        assertEquals(3, bookingData.getDays());
    }

    @Test
    public void testToString() {
        bookingData.setId(1);
        bookingData.setRoom(101);
        bookingData.setDays(3);
        assertEquals("BookingData [id=1, room=101, days=3]", bookingData.toString());
    }
}
