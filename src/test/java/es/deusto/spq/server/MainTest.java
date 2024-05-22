package es.deusto.spq.server;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class MainTest {

    private MockedStatic<GrizzlyHttpServerFactory> grizzlyHttpServerFactoryMock;
    private HttpServer httpServerMock;

    @Before
    public void setUp() {
        // Mock the GrizzlyHttpServerFactory
        grizzlyHttpServerFactoryMock = Mockito.mockStatic(GrizzlyHttpServerFactory.class);
        httpServerMock = mock(HttpServer.class);

        // Configure the mock to return our HttpServer mock
        grizzlyHttpServerFactoryMock.when(() -> GrizzlyHttpServerFactory.createHttpServer(any(URI.class), any(ResourceConfig.class)))
                .thenReturn(httpServerMock);
    }

    @After
    public void tearDown() {
        // Close the mocked static
        grizzlyHttpServerFactoryMock.close();
    }

    @Test
    public void testStartServer() {
        // Call the startServer method
        HttpServer server = Main.startServer();

        // Verify that the server is not null and has started
        assertNotNull(server);
        verify(httpServerMock, never()).shutdown(); // ensure shutdown was not called during start
    }

//    @Test
//    public void testMain() throws Exception {
        // Mock System.in to simulate user input
//        InputStream originalIn = System.in;
//        System.setIn(new ByteArrayInputStream("".getBytes()));

//        try {
            // Call the main method
//            Main.main(new String[0]);

//            // Verify that the server is started
//            verify(httpServerMock, times(1)).start();

//            // Verify that the server is shut down after user input
//            verify(httpServerMock, times(1)).shutdown();
//        } finally {
//            // Restore System.in
//            System.setIn(originalIn);
//        }
//    }
}
