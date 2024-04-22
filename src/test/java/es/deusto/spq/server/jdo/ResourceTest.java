package es.deusto.spq.server.jdo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.CustomerData;
import es.deusto.spq.server.Resource;

public class ResourceTest {

    @Mock
    private PersistenceManagerFactory pmf;

    @Mock
    private PersistenceManager pm;

    @Mock
    private Transaction tx;

    @InjectMocks
    private Resource resource;

    @SuppressWarnings("deprecation")
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(pmf.getPersistenceManager()).thenReturn(pm);
        when(pm.currentTransaction()).thenReturn(tx);
    }

    private CustomerData createCustomerData() {
        CustomerData data = new CustomerData();
        data.setEmail("email@example.com");
        data.setName("John");
        data.setSurname("Doe");
        data.setPassword("password123");
        data.setAddress("1234 Main St");
        data.setPhone(5551234);
        return data;
    }

    @Test
    public void testRegisterCustomer() {
        CustomerData customerData = createCustomerData();
        when(pm.getObjectById(eq(Customer.class), anyString())).thenThrow(new javax.jdo.JDOObjectNotFoundException());
        doNothing().when(pm).makePersistent(any(Customer.class));

        Response response = resource.registerCustomer(customerData);

        verify(tx).begin();
        verify(tx).commit();
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testLoginCustomer() {
        CustomerData customerData = createCustomerData();
        Customer mockCustomer = new Customer(customerData.getEmail(), customerData.getName(), customerData.getSurname(), customerData.getPassword(), customerData.getAddress(), customerData.getPhone());
        when(pm.getObjectById(Customer.class, "email@example.com")).thenReturn(mockCustomer);

        Response response = resource.loginCustomer(customerData);

        verify(tx).begin();
        verify(tx, times(1)).commit();  // Ensure transaction is committed once
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testLoginCustomerFailedPassword() {
        CustomerData customerData = createCustomerData();
        customerData.setPassword("wrongpassword");
        Customer mockCustomer = new Customer("email@example.com", "John", "Doe", "password123", "1234 Main St", 5551234);
        when(pm.getObjectById(Customer.class, "email@example.com")).thenReturn(mockCustomer);

        Response response = resource.loginCustomer(customerData);

        verify(tx).begin();
        verify(tx, times(1)).commit();  // Ensure transaction is committed once
        assertNotNull(response);
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testLoginCustomerNotFound() {
        CustomerData customerData = createCustomerData();
        customerData.setEmail("nonexistent@example.com");
        when(pm.getObjectById(Customer.class, "nonexistent@example.com")).thenThrow(new javax.jdo.JDOObjectNotFoundException());

        Response response = resource.loginCustomer(customerData);

        verify(tx).begin();
        verify(tx, times(1)).commit();  // Ensure transaction is committed once
        assertNotNull(response);
        assertEquals(404, response.getStatus());
    }

    // Additional test cases for other methods in the Resource class can be added here.
}
