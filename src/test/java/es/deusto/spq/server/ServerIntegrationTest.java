package es.deusto.spq.server;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.IntegrationTest;
import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.BookingData;
import es.deusto.spq.pojo.CustomerData;
import es.deusto.spq.pojo.RoomData;
import es.deusto.spq.server.jdo.Booking;
import es.deusto.spq.server.jdo.Customer;

@Category(IntegrationTest.class)
public class ServerIntegrationTest {

    private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    
    private static HttpServer server;
    private WebTarget target;

    @BeforeClass
    public static void prepareTests() throws Exception {
        server = Main.startServer();

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.makePersistent(new Customer("turin@example.com", "Alan", "Turin", "1234", "Turin Street 123", 123456789));
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    @Before
    public void setUp() {
        // Aquí puedes agregar lógica de configuración adicional si es necesaria
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI).path("resource");
    }

    @AfterClass
    public static void tearDownServer() throws Exception {
        server.shutdown();

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.newQuery(Customer.class).deletePersistentAll();
            pm.newQuery(Booking.class).deletePersistentAll();
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    @Test
    public void testRegisterCustomer() {
        CustomerData customerData = new CustomerData();
        // Set customer data here
        customerData.setEmail("turin@example.com");
        customerData.setPassword("1234");
        customerData.setName("Alan");
        customerData.setSurname("Turin");
        customerData.setAddress("Turin Street 123");
        customerData.setPhone(123456789);   

        Response response = target.path("registerCustomer")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(customerData, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

    @Test
    public void testLoginCustomer() {
        CustomerData customerData = new CustomerData();
        // Set customer data here
        customerData.setEmail("turin@example.com");
        customerData.setName("Alan");
        customerData.setSurname("Turin");
        customerData.setPassword("1234");
        customerData.setAddress("Turin Street 123");
        customerData.setPhone(123456789);

        Response response = target.path("loginCustomer")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(customerData, MediaType.APPLICATION_JSON));
        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());

    }

    @Test
    public void testLoginAdmin() {
        AdminData adminData = new AdminData();
        // Set admin data here
        adminData.setUserName("admin");
        adminData.setPassword("admin");

        Response response = target.path("loginAdmin")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(adminData, MediaType.APPLICATION_JSON));
        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

    @Test
    public void testBookRoom() {
        BookingData bookingData = new BookingData();
        bookingData.setRoom(101);
        bookingData.setCustomer("test@example.com");
        Date checkInDate = new Date(0); // Año 2024, mes 4 (0-indexado), día 30
        bookingData.setCheckIn(checkInDate);
        bookingData.setDays(3);

        Response response = target.path("bookRoom")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(bookingData, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

    @Test
    public void testRegisterRoom() {
        RoomData roomData = new RoomData();
        roomData.setNumber(101);
        roomData.setCapacity(2);
        roomData.setType("Double");
        roomData.setPrice(100.0);
        roomData.setDescription("Double room");

        Response response = target.path("registerRoom")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(roomData, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

}
