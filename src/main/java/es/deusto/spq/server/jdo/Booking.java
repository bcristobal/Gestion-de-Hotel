package es.deusto.spq.server.jdo;

import java.util.Date;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Represents a booking made by a customer for a room in a hotel.
 */
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

    /**
     * Constructs a new Booking object with the specified id, room, customer, check-in date, and number of days.
     *
     * @param id       the id of the booking
     * @param room     the room reserved for the booking
     * @param customer the customer who made the booking
     * @param date     the check-in date for the booking
     * @param days     the number of days for the booking
     */
    public Booking(int id, Room room, Customer customer, Date date, int days) {
        this.id = id;
        this.room = room;
        this.customer = customer;
        this.checkIn = date;
        this.days = days;
    }

    /**
     * Returns the id of the booking.
     *
     * @return the id of the booking
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the room reserved for the booking.
     *
     * @return the room reserved for the booking
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room reserved for the booking.
     *
     * @param room the room reserved for the booking
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Returns the customer who made the booking.
     *
     * @return the customer who made the booking
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer who made the booking.
     *
     * @param customer the customer who made the booking
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the check-in date for the booking.
     *
     * @return the check-in date for the booking
     */
    public Date getCheckIn() {
        return checkIn;
    }

    /**
     * Sets the check-in date for the booking.
     *
     * @param checkIn the check-in date for the booking
     */
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Returns the number of days for the booking.
     *
     * @return the number of days for the booking
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the number of days for the booking.
     *
     * @param days the number of days for the booking
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * Calculates and returns the price for the booking.
     *
     * @return the price for the booking
     */
    public double calculatePrice() {
        return this.room.getPrice() * this.days;
    }
}
