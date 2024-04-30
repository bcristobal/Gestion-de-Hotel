package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Room {
    @PrimaryKey
    int number = 0;
    int capacity = 0;  
    String type = "";
    double price = 0.0;
    String description = "";
    // Posible añadir más atributos como el historial de reservas, etc.

    public Room(int number, int capacity, String type, double price, String description) {
        this.number = number;
        this.capacity = capacity;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


    
