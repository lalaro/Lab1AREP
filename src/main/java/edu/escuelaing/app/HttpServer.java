package edu.escuelaing.app;

import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            OutputStream out = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean isFirstLine = true;
            String file = "";

            while ((inputLine = in.readLine()) != null) {
                if(isFirstLine){
                    file = inputLine.split(" ")[1];
                    isFirstLine = false;
                }

                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            URI requestedFile = new URI(file);

            System.out.println("file: " + requestedFile);

            // Si se solicita una imagen
            if(requestedFile.getPath().endsWith(".png")){
                File imgFile = new File("src/main/resources/image.png");
                if(imgFile.exists()) {
                    outputLine = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: image/png\r\n"  // Para image/png
                            + "\r\n";
                    out.write(outputLine.getBytes());

                    FileInputStream imgInputStream = new FileInputStream(imgFile);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = imgInputStream.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    imgInputStream.close();
                } else {
                    outputLine = "HTTP/1.1 404 Not Found\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<html><body><h1>Image Not Found</h1></body></html>";
                    out.write(outputLine.getBytes());
                }
            }
            else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n" +
                        "\r\n" +
                        "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <title>Form Example</title>"
                        + "    <meta charset=\"UTF-8\">"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                        + "</head>"
                        + "<body>"
                        + "    <h1>Form with GET</h1>"
                        + "    <form action=\"/hello\">"
                        + "        <label for=\"name\">Name:</label><br>"
                        + "        <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>"
                        + "        <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">"
                        + "    </form> "
                        + "    <div id=\"getrespmsg\"></div>"
                        + ""
                        + "    <script>"
                        + "        function loadGetMsg() {"
                        + "            let nameVar = document.getElementById(\"name\").value;"
                        + "            const xhttp = new XMLHttpRequest();"
                        + "            xhttp.onload = function() {"
                        + "                document.getElementById(\"getrespmsg\").innerHTML ="
                        + "                this.responseText;"
                        + "            }"
                        + "            xhttp.open(\"GET\", \"/hello?name=\"+nameVar);"
                        + "            xhttp.send();"
                        + "        }"
                        + "    </script>"
                        + ""
                        + "    <h1>Form with POST</h1>"
                        + "    <form action=\"/hellopost\">"
                        + "        <label for=\"postname\">Name:</label><br>"
                        + "        <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>"
                        + "        <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">"
                        + "    </form>"
                        + ""
                        + "    <div id=\"postrespmsg\"></div>"
                        + ""
                        + "    <script>"
                        + "        function loadPostMsg(name){"
                        + "            let url = \"/hellopost?name=\" + name.value;"
                        + ""
                        + "            fetch (url, {method: 'POST'})"
                        + "                .then(x => x.text())"
                        + "                .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);"
                        + "        }"
                        + "    </script>"
                        + "    <h1>Image Example</h1>"
                        + "    <img src=\"main/resources/image.png\" alt=\"Test Image\" width=\"300\">"
                        + "</body>"
                        + "</html>";
                out.write(outputLine.getBytes());
            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String helloRestService(String path, String query){
        String response = "HTTP/1.1 200 OK\r\n"
                +"Content-Type: application/json\r\n"
                +"\r\n"
                +"{\"name\": \"John\", \"age\":30, \"car\":null}";
        return response;
    }
}
