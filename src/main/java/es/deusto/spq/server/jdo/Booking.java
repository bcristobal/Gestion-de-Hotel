package es.deusto.spq.server.jdo;

import java.sql.Date;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class Booking {
    @PrimaryKey
    int id = 0;
    @Persistent(mappedBy = "booking", dependentElement = "true")
    Room room = null;
    @Persistent(mappedBy = "booking", dependentElement = "true")
    Customer customer = null;
    Date checkIn = null;
    int days = 0;
    // Posible añadir más atributos como el historial de reservas, etc.

    public Booking(int id, Room room, Customer customer, Date checkIn, int days) {
        this.id = id;
        this.room = room;
        this.customer = customer;
        this.checkIn = checkIn;
        this.days = days;
    }

    public int getId() {
        return id;
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

    public double calculatePrice() {
        return this.room.getPrice() * this.days;
    }
}
