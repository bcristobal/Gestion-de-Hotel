package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserDataTest {

    private UserData userData;

    @Before
    public void setUp() {
        userData = new UserData();
    }

    @Test
    public void testSetAndGetLogin() {
        String login = "user123";
        userData.setLogin(login);
        assertEquals(login, userData.getLogin());
    }

    @Test
    public void testSetAndGetPassword() {
        String password = "password123";
        userData.setPassword(password);
        assertEquals(password, userData.getPassword());
    }

    @Test
    public void testToString() {
        String login = "user123";
        String password = "password123";
        userData.setLogin(login);
        userData.setPassword(password);
        assertEquals("[login=user123, password=password123]", userData.toString());
    }
}
