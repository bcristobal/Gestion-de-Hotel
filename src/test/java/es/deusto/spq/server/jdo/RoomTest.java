package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {
    
    private Room room;

    @Before
    public void setUp() {
        room = new Room(101, 2, "Double", 100.0, "A double room with two beds and a bathroom.");
    }

    @Test
    public void testGetNumber() {
        assertEquals(101, room.getNumber());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(2, room.getCapacity());
    }

    @Test
    public void testSetCapacity() {
        room.setCapacity(3);
        assertEquals(3, room.getCapacity());
    }

    @Test
    public void testGetType() {
        assertEquals("Double", room.getType());
    }

    @Test
    public void testSetType() {
        room.setType("Single");
        assertEquals("Single", room.getType());
    }

    @Test
    public void testGetPrice() {
        assertEquals(100.0, room.getPrice(), 0.0);
    }

    @Test
    public void testSetPrice() {
        room.setPrice(120.0);
        assertEquals(120.0, room.getPrice(), 0.0);
    }

    @Test
    public void testGetDescription() {
        assertEquals("A double room with two beds and a bathroom.", room.getDescription());
    }

    @Test
    public void testSetDescription() {
        room.setDescription("A single room with one bed.");
        assertEquals("A single room with one bed.", room.getDescription());
    }
}
