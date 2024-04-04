package es.deusto.spq.pojo;

public class CustomerData {
    int id = 0;
    String name = null;
    String surname = null;
    String email = null;
    String password = null;
    String address = null;
    int phone = 0;
    // Posible añadir más atributos como el historial de reservas, etc.

    public CustomerData() {
        // required by serialization
    }

    public int getId() {
        return id;
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
    
}
