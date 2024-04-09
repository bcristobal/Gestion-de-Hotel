package es.deusto.spq.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.spq.server.jdo.User;
import es.deusto.spq.server.jdo.Booking;
import es.deusto.spq.server.jdo.Customer;
import es.deusto.spq.server.jdo.Message;
import es.deusto.spq.server.jdo.Room;
import es.deusto.spq.pojo.BookingData;
import es.deusto.spq.pojo.CustomerData;
import es.deusto.spq.pojo.DirectMessage;
import es.deusto.spq.pojo.MessageData;
import es.deusto.spq.pojo.RoomData;
import es.deusto.spq.pojo.UserData;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	@POST
	@Path("/sayMessage")
	public Response sayMessage(DirectMessage directMessage) {
		User user = null;
		try{
			tx.begin();
			logger.info("Creating query ...");
			
			try (Query<?> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \"" + directMessage.getUserData().getLogin() + "\" &&  password == \"" + directMessage.getUserData().getPassword() + "\"")) {
				q.setUnique(true);
				user = (User)q.execute();
				
				logger.info("User retrieved: {}", user);
				if (user != null)  {
					Message message = new Message(directMessage.getMessageData().getMessage());
					user.getMessages().add(message);
					pm.makePersistent(user);					 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		
		if (user != null) {
			cont++;
			logger.info(" * Client number: {}", cont);
			MessageData messageData = new MessageData();
			messageData.setMessage(directMessage.getMessageData().getMessage());
			return Response.ok(messageData).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Login details supplied for message delivery are not correct").build();
		}
	}
	
	@POST
	@Path("/register")
	public Response registerUser(UserData userData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", userData.getLogin());
			User user = null;
			try {
				user = pm.getObjectById(User.class, userData.getLogin());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("User: {}", user);
			if (user != null) {
				logger.info("Setting password user: {}", user);
				user.setPassword(userData.getPassword());
				logger.info("Password set user: {}", user);
			} else {
				logger.info("Creating user: {}", user);
				user = new User(userData.getLogin(), userData.getPassword());
				pm.makePersistent(user);					 
				logger.info("User created: {}", user);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}

	@POST
	@Path("/registerCustomer")
	public Response registerCustomer(CustomerData customerData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the customer already exits or not: '{}'", customerData.getEmail());
			Customer customer = null;
			try {
				customer = pm.getObjectById(Customer.class, customerData.getEmail());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Customer: {}", customer);
			if (customer != null) { // this customer already exists
				logger.info("This customer already exists: {}", customer);
			} else { // this customer does not exist
				logger.info("Creating customer: {}", customer);
				customer = new Customer(customerData.getEmail(), customerData.getName(), customerData.getSurname(), customerData.getPassword(), customerData.getAddress(), customerData.getPhone());
				pm.makePersistent(customer);	
				//TODO: Here I add some Rooms
				/*
				Room room = new Room(1, 2, "Double", 50, "Room with two beds");
				pm.makePersistent(room);
				Booking booking = new Booking(1, room, customer, "2020-12-01", 3);
				pm.makePersistent(booking);
				*/
				logger.info("Customer created: {}", customer);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}

	@POST
	@Path("/loginCustomer")
	public Response loginCustomer(CustomerData customerData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the customer exists or not: '{}'", customerData.getEmail());
			Customer customer = null;
			try {
				customer = pm.getObjectById(Customer.class, customerData.getEmail());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Customer: {}", customer);
			if (customer != null) { // this customer exists
				if(customer.getPassword().equals(customerData.getPassword())) { // password matches
					logger.info("Login successful for customer: {}", customer);
					tx.commit();
					return Response.ok().build();
				} else { // password does not match
					logger.info("Incorrect password for customer: {}", customer);
					tx.commit();
					return Response.status(Status.UNAUTHORIZED).build();
				}
			} else { // this customer does not exist
				logger.info("Customer does not exist: {}", customerData.getEmail());
				tx.commit();
				return Response.status(Status.NOT_FOUND).build();
			}
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}

	@POST
	@Path("/bookRoom")
	public Response bookRoom (BookingData bookingData) {
		try
		{	
			tx.begin();
			logger.info("Checking whether the room already exits or not: '{}'", bookingData.getRoom());
			Room room = null;
			try {
				room = pm.getObjectById(Room.class, bookingData.getRoom());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("room: {}", room);
			if (room != null) { // this room already exists
				logger.info("This room already exists: {}", room);
				logger.info("Checking whether the customer already exits or not: '{}'", bookingData.getCustomer());
				Customer customer = null;
				try {
					customer = pm.getObjectById(Customer.class, bookingData.getCustomer());
				} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
					logger.info("Exception launched: {}", jonfe.getMessage());
				}
				logger.info("customer: {}", customer);
				if (customer != null) { // this customer already exists
					logger.info("This customer already exists: {}", customer);
					logger.info("Creating booking: {}", bookingData);
					Booking booking = new Booking(bookingData.getId(), room, customer, bookingData.getCheckIn().toString(), bookingData.getDays());
					pm.makePersistent(booking);					 
					logger.info("Booking created: {}", booking);
				} else { // this customer does not exist
					logger.info("Customer does not exist: {}", bookingData.getCustomer());
				}
			} else { // this room does not exist
				logger.info("Room does not exist: {}", bookingData.getRoom());
			}
			tx.commit();
			return Response.ok().build();
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
	  
		}
	}

	@GET
	@Path("/getRooms")
	public static Response getRooms () {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Room> rooms = null;
		try {
			tx.begin();
			logger.info("Creating query ...");
			Query<Room> q = pm.newQuery(Room.class);
			rooms = q.executeList();
			logger.info("Rooms retrieved: {}", rooms);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return Response.ok(rooms).build();
	}

	@GET
	@Path("/getCustomers")
	public static Response getCustomers () {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Customer> customers = null;
		try {
			tx.begin();
			logger.info("Creating query ...");
			Query<Customer> q = pm.newQuery(Customer.class);
			customers = q.executeList();
			logger.info("Customers retrieved: {}", customers);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return Response.ok(customers).build();
	}
	
	@POST
	@Path("/registerRoom")
	public Response registerRoom(RoomData roomData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the room already exits or not: '{}'", roomData.getNumber());
			Room room = null;
			try {
				room = pm.getObjectById(Room.class, roomData.getNumber());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("room: {}", room);
			if (room != null) { // this room already exists
				logger.info("This room already exists: {}", room);
			} else { // this room does not exist
				logger.info("Creating room: {}", room);
				room = new Room(roomData.getNumber(), roomData.getCapacity(), roomData.getType(), roomData.getPrice(), roomData.getDescription());
				pm.makePersistent(room);					 
				logger.info("Room created: {}", room);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		return Response.ok("Hola Mundo(Hello World)").build();
	}
	
	@GET
	@Path("/myname")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayMyName() {
		return Response.ok("My name is Beñat").build();
	}

}
