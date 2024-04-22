package es.deusto.spq.server.jdo;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {
    
    private Admin admin;

    @Before
    public void setUp() {
        admin = new Admin("userAdmin", "securePassword123");
    }

    @Test
    public void testGetUserName() {
        assertEquals("userAdmin", admin.getUserName());
    }

    @Test
    public void testSetUserName() {
        admin.setUserName("newAdmin");
        assertEquals("newAdmin", admin.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("securePassword123", admin.getPassword());
    }

    @Test
    public void testSetPassword() {
        admin.setPassword("newPassword");
        assertEquals("newPassword", admin.getPassword());
    }

    @Test
    public void testToString() {
        assertEquals("Admin [userName=userAdmin, password=securePassword123]", admin.toString());
    }
}
