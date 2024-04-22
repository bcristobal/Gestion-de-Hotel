package es.deusto.spq.server.jdo;

import java.util.Date;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Booking {
    @PrimaryKey
    int id = 0;
    //@Persistent(mappedBy = "booking", dependentElement = "true")
    @ForeignKey
    Room room = null;
    //@Persistent(mappedBy = "booking", dependentElement = "true")
    @ForeignKey
    Customer customer = null;
    Date checkIn = null;
    int days = 0;
    // Posible añadir más atributos como el historial de reservas, etc.

    public Booking(int id, Room room, Customer customer, Date date, int days) {
        this.id = id;
        this.room = room;
        this.customer = customer;
        this.checkIn = date;
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
