package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * The Admin class represents an administrator in the system.
 * It contains information about the admin's username and password.
 */
@PersistenceCapable
public class Admin {
    @PrimaryKey
    String userName = null;
    String password = null;

    /**
     * Constructs a new Admin object with the specified username and password.
     *
     * @param userName the username of the admin
     * @param password the password of the admin
     */
    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Returns the username of the admin.
     *
     * @return the username of the admin
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Sets the username of the admin.
     *
     * @param userName the new username of the admin
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the password of the admin.
     *
     * @return the password of the admin
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the admin.
     *
     * @param password the new password of the admin
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the Admin object.
     *
     * @return a string representation of the Admin object
     */
    public String toString() {
        return "Admin [userName=" + userName + ", password=" + password + "]";
    }
}
