package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RoomDataTest {

    private RoomData roomData;

    @Before
    public void setUp() {
        roomData = new RoomData();
    }

    @Test
    public void testSetAndGetNumber() {
        int number = 101;
        roomData.setNumber(number);
        assertEquals(number, roomData.getNumber());
    }

    @Test
    public void testSetAndGetCapacity() {
        int capacity = 2;
        roomData.setCapacity(capacity);
        assertEquals(capacity, roomData.getCapacity());
    }

    @Test
    public void testSetAndGetType() {
        String type = "Individual";
        roomData.setType(type);
        assertEquals(type, roomData.getType());
    }

    @Test
    public void testSetAndGetPrice() {
        double price = 100.0;
        roomData.setPrice(price);
        assertEquals(price, roomData.getPrice(), 0.01);
    }

    @Test
    public void testSetAndGetDescription() {
        String description = "Esto es una habitación individual";
        roomData.setDescription(description);
        assertEquals(description, roomData.getDescription());
    }

    @Test
    public void testToString() {
        roomData.setNumber(101);
        roomData.setType("Individual");
        roomData.setPrice(100.0);
        assertEquals("RoomData [number=101, type=Individual, price=100.0]", roomData.toString());
    }
}
