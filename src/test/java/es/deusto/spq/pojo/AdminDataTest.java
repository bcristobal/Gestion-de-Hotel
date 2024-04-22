package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AdminDataTest {

    AdminData adminData;

    @Before
    public void setUp(){
        adminData = new AdminData();
        adminData.setUserName("test-userName");
        adminData.setPassword("test-passwrd");
    }

    @Test
    public void testGetUserName() {
        assertEquals("test-userName", adminData.getUserName());
    }
    @Test
    public void testSetUserName() {
        assertEquals("test-userName", adminData.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("test-passwrd", adminData.getPassword());
    }
    @Test
    public void testSettPassword() {
        assertEquals("test-passwrd", adminData.getPassword());
    }
}
