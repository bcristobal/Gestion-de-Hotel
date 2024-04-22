package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CustomerDataTest {

    private CustomerData customerData;

    @Before
    public void setUp() {
        customerData = new CustomerData();
    }

    @Test
    public void testSetAndGetEmail() {
        customerData.setEmail("customer@example.com");
        assertEquals("customer@example.com", customerData.getEmail());
    }

    @Test
    public void testSetAndGetName() {
        customerData.setName("John");
        assertEquals("John", customerData.getName());
    }

    @Test
    public void testSetAndGetSurname() {
        customerData.setSurname("Doe");
        assertEquals("Doe", customerData.getSurname());
    }

    @Test
    public void testSetAndGetPassword() {
        customerData.setPassword("password123");
        assertEquals("password123", customerData.getPassword());
    }

    @Test
    public void testSetAndGetAddress() {
        customerData.setAddress("123 Main St");
        assertEquals("123 Main St", customerData.getAddress());
    }

    @Test
    public void testSetAndGetPhone() {
        customerData.setPhone(123456789);
        assertEquals(123456789, customerData.getPhone());
    }
}
