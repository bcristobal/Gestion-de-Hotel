package es.deusto.spq.client;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.BookingData;
import es.deusto.spq.pojo.CustomerData;
import es.deusto.spq.pojo.RoomData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Container {
    protected static final Logger logger = LogManager.getLogger();

	private static String email = null;
	private static int bookId = 0;

    private Client client;
	private WebTarget webTarget;

    public Container(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}

    public void registerCustomer(String email, String name, String surname, String password, String address, int phone) {
		WebTarget registerCustomerWebTarget = webTarget.path("registerCustomer");
		Invocation.Builder invocationBuilder = registerCustomerWebTarget.request(MediaType.APPLICATION_JSON);
		
		CustomerData customerData = new CustomerData();
		customerData.setEmail(email);
		customerData.setName(name);
		customerData.setSurname(surname);
		customerData.setPassword(password);
		customerData.setAddress(address);
		customerData.setPhone(phone);
		Response response = invocationBuilder.post(Entity.entity(customerData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("Customer correctly registered");
		}
	}
			
		public boolean loginCustomer (String email, String password) {
			WebTarget loginCustomerWebTarget = webTarget.path("loginCustomer");
			Invocation.Builder invocationBuilder = loginCustomerWebTarget.request(MediaType.APPLICATION_JSON);
			
			CustomerData customerData = new CustomerData();
			customerData.setEmail(email);
			customerData.setPassword(password);
			Response response = invocationBuilder.post(Entity.entity(customerData, MediaType.APPLICATION_JSON));
			if (response.getStatus() != Status.OK.getStatusCode()) {
				logger.error("Error connecting with the server. Code: {}, Endpoint: {}", response.getStatus(), loginCustomerWebTarget.getUri());
				return false;
			} else {
				Container.email = email;
				logger.info("Customer correctly logged in");
				return true;
			}
		}

		public boolean loginAdmin (String userName, String password) {
			WebTarget loginAdminWebTarget = webTarget.path("loginAdmin");
			Invocation.Builder invocationBuilder = loginAdminWebTarget.request(MediaType.APPLICATION_JSON);
			
			AdminData adminData = new AdminData();
			adminData.setUserName(userName);
			adminData.setPassword(password);
			Response response = invocationBuilder.post(Entity.entity(adminData, MediaType.APPLICATION_JSON));
			if (response.getStatus() != Status.OK.getStatusCode()) {
				logger.error("Error connecting with the server. Code: {}", response.getStatus());
				return false;
			} else {
				logger.info("Admin correctly logged in");
				return true;
			}
		}

		public boolean bookRoom(int roomNumber, Date checkIn, int days) {
			WebTarget bookRoomWebTarget = webTarget.path("bookRoom");
			Invocation.Builder invocationBuilder = bookRoomWebTarget.request(MediaType.APPLICATION_JSON);
			
			BookingData bookingData = new BookingData();
			bookingData.setId(bookId++);
			bookingData.setRoom(roomNumber);
			bookingData.setCustomer(email);
			bookingData.setCheckIn(checkIn);
			bookingData.setDays(days);
			Response response = invocationBuilder.post(Entity.entity(bookingData, MediaType.APPLICATION_JSON));
			if (response.getStatus() != Status.OK.getStatusCode()) {
				logger.error("Error connecting with the server. Code: {}", response.getStatus());
				return false;
			} else {
				logger.info("Room correctly booked");
				return true;
			}
		}

		public List<RoomData> getRooms() {
			WebTarget getRoomsWebTarget = webTarget.path("getRooms");
			Invocation.Builder invocationBuilder = getRoomsWebTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();
			if (response.getStatus() != Status.OK.getStatusCode()) {
				logger.error("Error connecting with the server. Code: {}", response.getStatus());
				return null;
			} else {
				logger.info("Rooms correctly retrieved");
				return response.readEntity(new GenericType<List<RoomData>>() {});
			}
		}

		public static void main(String[] args) {
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		Container container = new Container(hostname, port);

		new Main(container);

		//container.registerCustomer("example@example.com", "Hello", "World", "root1234", "Baker Street", 123456789);
		
		
	}
}
