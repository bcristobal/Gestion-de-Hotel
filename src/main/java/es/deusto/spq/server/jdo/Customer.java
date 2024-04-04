package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

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

    public Customer(String email, String name, String surname, String password, String address, int phone) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String toString() {
        return "[email=" + email + ", name=" + name + ", surname=" + surname + ", password=" + password + ", address=" + address + ", phone=" + phone + "]";
    }
}
