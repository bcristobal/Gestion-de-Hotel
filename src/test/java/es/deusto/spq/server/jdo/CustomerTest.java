package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
    
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("john.doe@example.com", "John", "Doe", "secure123", "1234 Main St", 5551234);
    }

    @Test
    public void testGetName() {
        assertEquals("John", customer.getName());
    }

    @Test
    public void testSetName() {
        customer.setName("Jane");
        assertEquals("Jane", customer.getName());
    }

    @Test
    public void testGetSurname() {
        assertEquals("Doe", customer.getSurname());
    }

    @Test
    public void testSetSurname() {
        customer.setSurname("Smith");
        assertEquals("Smith", customer.getSurname());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", customer.getEmail());
    }

    @Test
    public void testSetEmail() {
        customer.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", customer.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("secure123", customer.getPassword());
    }

    @Test
    public void testSetPassword() {
        customer.setPassword("newPassword123");
        assertEquals("newPassword123", customer.getPassword());
    }

    @Test
    public void testGetAddress() {
        assertEquals("1234 Main St", customer.getAddress());
    }

    @Test
    public void testSetAddress() {
        customer.setAddress("4321 First Ave");
        assertEquals("4321 First Ave", customer.getAddress());
    }

    @Test
    public void testGetPhone() {
        assertEquals(5551234, customer.getPhone());
    }

    @Test
    public void testSetPhone() {
        customer.setPhone(5554321);
        assertEquals(5554321, customer.getPhone());
    }

    @Test
    public void testToString() {
        String expected = "[email=john.doe@example.com, name=John, surname=Doe, password=secure123, address=1234 Main St, phone=5551234]";
        assertEquals(expected, customer.toString());
    }
}
