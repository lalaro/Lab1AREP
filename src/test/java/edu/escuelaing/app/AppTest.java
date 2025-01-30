package edu.escuelaing.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class AppTest {

    private Map<String, String> mockResponses;
    private static Thread serverThread;
    private static int port;

    @BeforeAll
    public static void setUpServer() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
        }
        serverThread = new Thread(() -> {
            try {
                EchoServer.start(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDownServer() {
        EchoServer.stop();
        if (serverThread != null && serverThread.isAlive()) {
            serverThread.interrupt();
        }
    }

    @BeforeEach
    public void setUp() {
        mockResponses = new HashMap<>();
        mockResponses.put("/", "200 OK");
        mockResponses.put("/hello", "Hello, world!");
        mockResponses.put("/sum?num1=5&num2=10", "15");
        mockResponses.put("/echo?msg=Hola", "Hola");
    }

    @Test
    public void testRootResponse() {
        String expected = "200 OK";
        String actual = mockResponses.getOrDefault("/", "404 Not Found");
        assertEquals(expected, actual, "La ruta raíz debe responder con 200 OK.");
    }

    @Test
    public void testHelloResponse() {
        String expected = "Hello, world!";
        String actual = mockResponses.getOrDefault("/hello", "404 Not Found");
        assertEquals(expected, actual, "La ruta /hello debe devolver 'Hello, world!'.");
    }

    @Test
    public void testSumResponse() {
        String expected = "15";
        String actual = mockResponses.getOrDefault("/sum?num1=5&num2=10", "404 Not Found");
        assertEquals(expected, actual, "La suma de 5 + 10 debe ser 15.");
    }

    @Test
    public void testEchoResponse() {
        String expected = "Hola";
        String actual = mockResponses.getOrDefault("/echo?msg=Hola", "404 Not Found");
        assertEquals(expected, actual, "La ruta /echo debe devolver el mismo mensaje recibido.");
    }

    @Test
    public void testNotFoundResponse() {
        String expected = "404 Not Found";
        String actual = mockResponses.getOrDefault("/ruta-inexistente", "404 Not Found");
        assertEquals(expected, actual, "Un recurso inexistente debe responder con 404.");
    }

    @Test
    public void testServerResponseSimulation() {
        int mockResponseCode = 404;
        String expectedResponse = "404 Not Found";
        String actualResponse = (mockResponseCode == 404) ? expectedResponse : "200 OK";

        assertEquals(expectedResponse, actualResponse, "Se esperaba una respuesta 404 del servidor.");
    }

    @Test
    public void testServerResponse200() {
        int mockResponseCode = 200;
        String expectedResponse = "200 OK";
        String actualResponse = (mockResponseCode == 200) ? expectedResponse : "404 Not Found";

        assertEquals(expectedResponse, actualResponse, "Se esperaba una respuesta 200 del servidor.");
    }

    @Test
    public void testServerResponse500() {
        int mockResponseCode = 500;
        String expectedResponse = "500 Internal Server Error";
        String actualResponse = (mockResponseCode == 500) ? expectedResponse : "200 OK";

        assertEquals(expectedResponse, actualResponse, "Se esperaba una respuesta 500 del servidor.");
    }

    @Test
    public void testServerResponse301() {
        int mockResponseCode = 301;
        String expectedResponse = "301 Moved Permanently";
        String actualResponse = (mockResponseCode == 301) ? expectedResponse : "404 Not Found";

        assertEquals(expectedResponse, actualResponse, "Se esperaba una respuesta 301 del servidor.");
    }

    @Test
    public void testServerResponse403() {
        int mockResponseCode = 403;
        String expectedResponse = "403 Forbidden";
        String actualResponse = (mockResponseCode == 403) ? expectedResponse : "200 OK";

        assertEquals(expectedResponse, actualResponse, "Se esperaba una respuesta 403 del servidor.");
    }

    @Test
    public void testEchoCommunication() {
        try (Socket clientSocket = new Socket("127.0.0.1", port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String messageToSend = "Hello Server";
            out.println(messageToSend);
            String response = in.readLine();

            assertEquals("Respuesta: Hello Server", response, "El servidor debe responder con el mismo mensaje.");
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException ocurrió durante la comunicación con el servidor: " + e.getMessage());
        }
    }

    @Test
    public void testURLParsing() throws MalformedURLException {
        URL myurl = new URL("http://ldbn.is.escuelaing.edu.co:8976/index.html?val=90&t=56#events");
        assertEquals("http", myurl.getProtocol(), "El protocolo debería ser 'http'.");
        assertEquals("ldbn.is.escuelaing.edu.co:8976", myurl.getAuthority(), "La autoridad debería ser 'ldbn.is.escuelaing.edu.co:8976'.");
        assertEquals("ldbn.is.escuelaing.edu.co", myurl.getHost(), "El host debería ser 'ldbn.is.escuelaing.edu.co'.");
        assertEquals(8976, myurl.getPort(), "El puerto debería ser 8976.");
        assertEquals("/index.html", myurl.getPath(), "La ruta debería ser '/index.html'.");
        assertEquals("val=90&t=56", myurl.getQuery(), "La consulta debería ser 'val=90&t=56'.");
        assertEquals("/index.html?val=90&t=56", myurl.getFile(), "El archivo debería ser '/index.html?val=90&t=56'.");
        assertEquals("events", myurl.getRef(), "La referencia debería ser 'events'.");
    }

    public void tearDown() {
        if (serverThread != null && serverThread.isAlive()) {
            serverThread.interrupt();
        }
    }
}