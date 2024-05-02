package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserDataTest {

    @Test
    public void testGetLogin() {
        UserData userData = new UserData();
        userData.setLogin("testLogin");
        assertEquals("testLogin", userData.getLogin());
    }

    @Test
    public void testSetLogin() {
        UserData userData = new UserData();
        userData.setLogin("testLogin");
        assertEquals("testLogin", userData.getLogin());
    }

    @Test
    public void testGetPassword() {
        UserData userData = new UserData();
        userData.setPassword("testPassword");
        assertEquals("testPassword", userData.getPassword());
    }

    @Test
    public void testSetPassword() {
        UserData userData = new UserData();
        userData.setPassword("testPassword");
        assertEquals("testPassword", userData.getPassword());
    }

    @Test
    public void testToString() {
        UserData userData = new UserData();
        userData.setLogin("testLogin");
        userData.setPassword("testPassword");
        assertEquals("[login=testLogin, password=testPassword]", userData.toString());
    }
}
