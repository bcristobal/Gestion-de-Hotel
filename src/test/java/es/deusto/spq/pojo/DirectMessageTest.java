package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DirectMessageTest {

    @Test
    public void testSetUserData() {
        DirectMessage directMessage = new DirectMessage();
        UserData userData = new UserData();
        directMessage.setUserData(userData);
        assertEquals(userData, directMessage.getUserData());
    }

    @Test
    public void testGetUserData() {
        DirectMessage directMessage = new DirectMessage();
        UserData userData = new UserData();
        directMessage.setUserData(userData);
        assertEquals(userData, directMessage.getUserData());
    }

    @Test
    public void testSetMessageData() {
        DirectMessage directMessage = new DirectMessage();
        MessageData messageData = new MessageData();
        directMessage.setMessageData(messageData);
        assertEquals(messageData, directMessage.getMessageData());
    }

    @Test
    public void testGetMessageData() {
        DirectMessage directMessage = new DirectMessage();
        MessageData messageData = new MessageData();
        directMessage.setMessageData(messageData);
        assertEquals(messageData, directMessage.getMessageData());
    }
}
