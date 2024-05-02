package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MessageDataTest {

    @Test
    public void testGetMessage() {
        MessageData messageData = new MessageData();
        messageData.setMessage("Test message");
        assertEquals("Test message", messageData.getMessage());
    }

    @Test
    public void testSetMessage() {
        MessageData messageData = new MessageData();
        messageData.setMessage("Test message");
        assertEquals("Test message", messageData.getMessage());
    }
}
