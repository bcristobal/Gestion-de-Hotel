package es.deusto.spq.pojo;

import java.sql.Date;

import es.deusto.spq.server.jdo.Customer;
import es.deusto.spq.server.jdo.Room;

public class BookingData {

	private int id;
	private Room room;
	private Customer customer;
	private Date checkIn;
	private int days;
	
	public BookingData() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "BookingData [id=" + id + ", room=" + room + ", days=" + days + "]";
	}
	
}
