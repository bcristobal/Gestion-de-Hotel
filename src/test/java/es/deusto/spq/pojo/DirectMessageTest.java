package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class DirectMessageTest {

    private DirectMessage directMessage;

    @Before
    public void setUp() {
        directMessage = new DirectMessage();
    }

    @Test
    public void testSetAndGetUserData() {
        UserData userData = new UserData();
        directMessage.setUserData(userData);
        assertNotNull(directMessage.getUserData());
        assertEquals(userData, directMessage.getUserData());
    }

    @Test
    public void testSetAndGetMessageData() {
        MessageData messageData = new MessageData();
        directMessage.setMessageData(messageData);
        assertNotNull(directMessage.getMessageData());
        assertEquals(messageData, directMessage.getMessageData());
    }
}
