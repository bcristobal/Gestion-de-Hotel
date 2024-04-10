package es.deusto.spq.pojo;

public class AdminData {
    String userName = null;
    String password = null;

    public AdminData() {
        // required by serialization
    }

    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
