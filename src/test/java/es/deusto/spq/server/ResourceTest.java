package es.deusto.spq.server;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.CustomerData;
import es.deusto.spq.server.jdo.Customer;

public class ResourceTest {
    private Resource resource;

    @Mock
    private PersistenceManager persistenceManager;

    @Mock
    private Transaction transaction;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // initializing static mock object PersistenceManagerFactory
        try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
            PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
            jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
            
            when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
            when(persistenceManager.currentTransaction()).thenReturn(transaction);

            // instantiate tested object with mock dependencies
            resource = new Resource();
        }
    }

    @Test
    public void testRegisterCustomer() {
        // prepare mock Persistence Manager to return User
        CustomerData customerData = new CustomerData();
        customerData.setEmail("turin@example.com");
        customerData.setPassword("1234");
        customerData.setName("Alan");
        customerData.setSurname("Turin");
        customerData.setAddress("Turin Street 123");
        customerData.setPhone(123456789);

        // simulate that
        Customer customer = spy(Customer.class);
        when(persistenceManager.getObjectById(Customer.class, customerData.getEmail())).thenThrow(new JDOObjectNotFoundException());
        when(customer.getEmail()).thenReturn(customerData.getEmail());
        when(customer.getPassword()).thenReturn(customerData.getPassword());
        when(customer.getName()).thenReturn(customerData.getName());
        when(customer.getSurname()).thenReturn(customerData.getSurname());
        when(customer.getAddress()).thenReturn(customerData.getAddress());
        when(customer.getPhone()).thenReturn(customerData.getPhone());

        //call tested method
        Response response = resource.registerCustomer(customerData);

        //check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

        @Test
        public void testLoginCustomer() {
            // Prepare test data
            CustomerData customerData = new CustomerData();
            customerData.setEmail("turin@example.com");
            customerData.setPassword("1234");
            customerData.setName("Alan");
            customerData.setSurname("Turin");
            customerData.setAddress("Turin Street 123");
            customerData.setPhone(123456789);

            // simulate that
            Customer customer = spy(Customer.class);
            when(persistenceManager.getObjectById(Customer.class, customerData.getEmail())).thenThrow(new JDOObjectNotFoundException());
            when(customer.getEmail()).thenReturn(customerData.getEmail());
            when(customer.getPassword()).thenReturn(customerData.getPassword());
            when(customer.getName()).thenReturn(customerData.getName());
            when(customer.getSurname()).thenReturn(customerData.getSurname());
            when(customer.getAddress()).thenReturn(customerData.getAddress());
            when(customer.getPhone()).thenReturn(customerData.getPhone());
           
            // Call the method under test
            Response response = resource.loginCustomer(customerData);
            // Check the expected response
            assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
            
            // Add a customer to try the login method
            CustomerData existingCustomerData = new CustomerData();
            existingCustomerData.setEmail("existing@example.com");
            existingCustomerData.setPassword("abcd");
            existingCustomerData.setName("Existing");
            existingCustomerData.setSurname("Customer");
            existingCustomerData.setAddress("Existing Street 456");
            existingCustomerData.setPhone(987654321);

            Customer existingCustomer = spy(Customer.class);
            when(persistenceManager.getObjectById(Customer.class, existingCustomerData.getEmail())).thenReturn(existingCustomer);
            when(existingCustomer.getEmail()).thenReturn(existingCustomerData.getEmail());
            when(existingCustomer.getPassword()).thenReturn(existingCustomerData.getPassword());
            when(existingCustomer.getName()).thenReturn(existingCustomerData.getName());
            when(existingCustomer.getSurname()).thenReturn(existingCustomerData.getSurname());
            when(existingCustomer.getAddress()).thenReturn(existingCustomerData.getAddress());
            when(existingCustomer.getPhone()).thenReturn(existingCustomerData.getPhone());

            // Call the method under test with existing customer
            Response responseExisting = resource.loginCustomer(existingCustomerData);
            // Check the expected response
            assertEquals(Response.Status.OK, responseExisting.getStatusInfo());
            
        }
}