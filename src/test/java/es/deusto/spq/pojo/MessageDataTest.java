package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MessageDataTest {

    private MessageData messageData;

    @Before
    public void setUp() {
        messageData = new MessageData();
    }

    @Test
    public void testSetAndGetMessage() {
        String message = "Test message";
        messageData.setMessage(message);
        assertEquals(message, messageData.getMessage());
    }
}
