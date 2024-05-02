package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

public class BookingTest {
    
    private Booking booking;
    private Room room;
    private Customer customer;
    private Date checkIn;

    @Before
    public void setUp() {
        room = new Room(1, 1, "Double", 100, "Double room");
        customer = new Customer("turin@example.com", "Alan", "Turin", "1234", "Turin Street 123", 1234567890);
        checkIn = new Date();
        booking = new Booking(1, room, customer, checkIn, 3);
    }

    @Test
    public void testGetId() {
        assertEquals(1, booking.getId());
    }

    @Test
    public void testGetRoom() {
        assertEquals(room, booking.getRoom());
    }

    @Test
    public void testSetRoom() {
        Room newRoom = new Room(1, 1, "Double", 100, "Double room");
        booking.setRoom(newRoom);
        assertEquals(newRoom, booking.getRoom());
    }

    @Test
    public void testGetCustomer() {
        assertEquals(customer, booking.getCustomer());
    }

    @Test
    public void testSetCustomer() {
        Customer newCustomer = new Customer("turin@example.com", "Alan", "Turin", "1234", "Turin Street 123", 1234567890);
        booking.setCustomer(newCustomer);
        assertEquals(newCustomer, booking.getCustomer());
    }

    @Test
    public void testGetCheckIn() {
        assertEquals(checkIn, booking.getCheckIn());
    }

    @Test
    public void testSetCheckIn() {
        Date newCheckIn = new Date();
        booking.setCheckIn(newCheckIn);
        assertEquals(newCheckIn, booking.getCheckIn());
    }

    @Test
    public void testGetDays() {
        assertEquals(3, booking.getDays());
    }

    @Test
    public void testSetDays() {
        booking.setDays(5);
        assertEquals(5, booking.getDays());
    }

    @Test
    public void testCalculatePrice() {
        room.setPrice(100);
        assertEquals(300.0, booking.calculatePrice(), 0.01);
    }
}
