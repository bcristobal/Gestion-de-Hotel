package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Represents a room in a hotel.
 */
@PersistenceCapable
public class Room {
    @PrimaryKey
    int number = 0;
    int capacity = 0;  
    String type = "";
    double price = 0.0;
    String description = "";
    // Posible añadir más atributos como el historial de reservas, etc.

    /**
     * Constructs a new Room object with the specified details.
     *
     * @param number      the room number
     * @param capacity    the room capacity
     * @param type        the room type
     * @param price       the room price
     * @param description the room description
     */
    public Room(int number, int capacity, String type, double price, String description) {
        this.number = number;
        this.capacity = capacity;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    /**
     * Returns the room number.
     *
     * @return the room number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the room number.
     *
     * @param number the room number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the room capacity.
     *
     * @return the room capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the room capacity.
     *
     * @param capacity the room capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the room type.
     *
     * @return the room type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the room type.
     *
     * @param type the room type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the room price.
     *
     * @return the room price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the room price.
     *
     * @param price the room price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the room description.
     *
     * @return the room description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the room description.
     *
     * @param description the room description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}


    
