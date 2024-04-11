package es.deusto.spq.pojo;

import java.util.Date;

public class BookingData {

	private int id;
	private int roomNumber;
	private String customerEmail;
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

	public int getRoom() {
		return roomNumber;
	}

	public void setRoom(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getCustomer() {
		return customerEmail;
	}

	public void setCustomer(String customerEmail) {
		this.customerEmail = customerEmail;
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
		return "BookingData [id=" + id + ", room=" + roomNumber + ", days=" + days + "]";
	}
	
}
