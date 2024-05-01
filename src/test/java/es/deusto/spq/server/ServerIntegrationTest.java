package es.deusto.spq.server;

import static org.junit.Assert.assertEquals;

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
import es.deusto.spq.pojo.CustomerData;
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
            pm.makePersistent(new Customer("turin@example.com", "1234", "Alan", "Turin", "Turin Street 123", 123456789));
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
        customerData.setPassword("1234");
        customerData.setName("Alan");
        customerData.setSurname("Turin");
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
        adminData.setPassword("1234");

        Response response = target.path("loginAmin")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(adminData, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }
}
