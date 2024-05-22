package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Represents a customer in the hotel management system.
 */
@PersistenceCapable
public class Customer {
    @PrimaryKey
    String email = null;
    String name = null;
    String surname = null;
    String password = null;
    String address = null;
    int phone = 0;
    // Posible añadir más atributos como el historial de reservas, etc.

    /**
     * Constructs a new Customer object with the specified details.
     *
     * @param email    the email of the customer
     * @param name     the name of the customer
     * @param surname  the surname of the customer
     * @param password the password of the customer
     * @param address  the address of the customer
     * @param phone    the phone number of the customer
     */
    public Customer(String email, String name, String surname, String password, String address, int phone) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Returns the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name the name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the surname of the customer.
     *
     * @return the surname of the customer
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the customer.
     *
     * @param surname the surname of the customer
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the email of the customer.
     *
     * @return the email of the customer
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email of the customer.
     *
     * @param email the email of the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the customer.
     *
     * @return the password of the customer
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the customer.
     *
     * @param password the password of the customer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the address of the customer.
     *
     * @return the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address the address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone the phone number of the customer
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the Customer object.
     *
     * @return a string representation of the Customer object
     */
    public String toString() {
        return "[email=" + email + ", name=" + name + ", surname=" + surname + ", password=" + password + ", address=" + address + ", phone=" + phone + "]";
    }
}
