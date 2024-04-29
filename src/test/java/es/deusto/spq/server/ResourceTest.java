package es.deusto.spq.server;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jdo.JDOHelper;
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

import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.CustomerData;
import es.deusto.spq.server.jdo.Admin;
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

            // Call the method under test
            Response response = resource.loginCustomer(customerData);
            // Check the expected response
            assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
            
            // Create a mock CustomerData object
            Customer mockCustomer = mock(Customer.class);
            when(mockCustomer.getEmail()).thenReturn("existing@example.com");
            when(mockCustomer.getPassword()).thenReturn("password");
            // Prepare the mock Persistence Manager to return the mock CustomerData
            when(persistenceManager.getObjectById(Customer.class, "existing@example.com")).thenReturn(mockCustomer);

            CustomerData existingCustomerData = new CustomerData();
            existingCustomerData.setEmail("existing@example.com");
            existingCustomerData.setPassword("password");
            existingCustomerData.setName("Alan");
            existingCustomerData.setSurname("Turin");
            existingCustomerData.setAddress("Turin Street 123");
            existingCustomerData.setPhone(123456789);

            // Call the method under test with existing customer
            Response responseExisting = resource.loginCustomer(existingCustomerData);
            // Check the expected response
            assertEquals(Response.Status.OK, responseExisting.getStatusInfo());
            
        }

        @Test
        public void testLoginAdmin() {
            // Prepare test data
            AdminData adminData = new AdminData();
            adminData.setUserName("admin");
            adminData.setPassword("1234");

            // Call the method under test
            Response response = resource.loginAmin(adminData);
            // Check the expected response
            assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
            
            // Create a mock CustomerData object
            Admin mockAdmin = mock(Admin.class);
            when(mockAdmin.getUserName()).thenReturn("existingAdmin");
            when(mockAdmin.getPassword()).thenReturn("password");
            // Prepare the mock Persistence Manager to return the mock CustomerData
            when(persistenceManager.getObjectById(Admin.class, "existingAdmin")).thenReturn(mockAdmin);

            AdminData existingAdminData = new AdminData();
            existingAdminData.setUserName("existingAdmin");
            existingAdminData.setPassword("password");

            // Call the method under test with existing customer
            Response responseExisting = resource.loginAmin(existingAdminData);
            // Check the expected response
            assertEquals(Response.Status.OK, responseExisting.getStatusInfo()); 
        }
        
        //TODO: Test bookRoom, getBookings, getCustomers, getAdmins

        @Test
        @SuppressWarnings("static-access")
        public void testGetRooms() {
            try (Response response = resource.getRooms()) {
                // Check the expected response
                assertEquals(Response.Status.OK, response.getStatusInfo());
            } catch (Exception e) {
            }
        }

        @Test
        @SuppressWarnings("static-access")
        public void testGetCustomers() {
            // Call the method under test
            Response response = resource.getCustomers();
            // Check the expected response
            assertEquals(Response.Status.OK, response.getStatusInfo());
        }
}