package es.deusto.spq.server;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

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
import es.deusto.spq.pojo.BookingData;
import es.deusto.spq.pojo.CustomerData;
import es.deusto.spq.pojo.RoomData;
import es.deusto.spq.server.jdo.Admin;
import es.deusto.spq.server.jdo.Customer;
import es.deusto.spq.server.jdo.Room;


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
        
        @Test
        public void testBookRoom() {
            // Prepare test data
            RoomData roomData = new RoomData();
            roomData.setNumber(1);
            roomData.setCapacity(2);
            roomData.setType("Double");
            roomData.setPrice(100);
            roomData.setDescription("A big room with a double bed");

            CustomerData customerData = new CustomerData();
            customerData.setEmail("turin@example.com");
            customerData.setPassword("1234");
            customerData.setName("Alan");
            customerData.setSurname("Turin");
            customerData.setAddress("Turin Street 123");
            customerData.setPhone(123456789);

            BookingData bookingData = new BookingData();
            bookingData.setId(0);
            bookingData.setRoom(roomData.getNumber());
            bookingData.setCustomer(customerData.getEmail());
            Date date = new Date();
            bookingData.setCheckIn(date);
            bookingData.setDays(3);
            
            // Call the method under test
            Response response = resource.bookRoom(bookingData);
            // Check the expected response
            assertEquals(Response.Status.OK, response.getStatusInfo());

            Room mockRoom = mock(Room.class);
            when(mockRoom.getNumber()).thenReturn(1);
            when(mockRoom.getCapacity()).thenReturn(2);
            when(mockRoom.getType()).thenReturn("Double");
            when(mockRoom.getPrice()).thenReturn(100.00);
            when(mockRoom.getDescription()).thenReturn("A big room with a double bed");
            when(persistenceManager.getObjectById(Room.class, 1)).thenReturn(mockRoom);

            Customer mockCustomer = mock(Customer.class);
            when(mockCustomer.getEmail()).thenReturn("turin@example.com");
            when(mockCustomer.getPassword()).thenReturn("1234");
            when(mockCustomer.getName()).thenReturn("Alan");
            when(mockCustomer.getSurname()).thenReturn("Turin");
            when(mockCustomer.getAddress()).thenReturn("Turin Street 123");
            when(mockCustomer.getPhone()).thenReturn(123456789);
            when(persistenceManager.getObjectById(Customer.class, "turin@example.com")).thenReturn(mockCustomer);

             // Call the method under test
            response = resource.bookRoom(bookingData);
             // Check the expected response
            assertEquals(Response.Status.OK, response.getStatusInfo());
        }

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
            try (Response response = resource.getCustomers()) {
                // Check the expected response
                assertEquals(Response.Status.OK, response.getStatusInfo());
            } catch (Exception e) {
            }
        }

        @Test
    public void testRegisterRoom() {
        // prepare mock Persistence Manager to return User
        RoomData roomData = new RoomData();
        roomData.setNumber(1);
        roomData.setCapacity(2);
        roomData.setType("Double");
        roomData.setPrice(100);
        roomData.setDescription("A big room with a double bed");

        //call tested method
        Response response = resource.registerRoom(roomData);

        //check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testDeleteRoom() {
        // Prepare test data
        RoomData roomData = new RoomData();
        roomData.setNumber(1);

        Room mockRoom = mock(Room.class);
        when(persistenceManager.getObjectById(Room.class, 1)).thenReturn(mockRoom);
        Response response = resource.deleteRoom(roomData);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        when(persistenceManager.getObjectById(Room.class, 2)).thenThrow(new javax.jdo.JDOObjectNotFoundException());
        roomData.setNumber(2);
        Response responseNotFound = resource.deleteRoom(roomData);
        assertEquals(Response.Status.OK.getStatusCode(), responseNotFound.getStatus());
    }

}