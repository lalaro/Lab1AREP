package edu.escuelaing.app.RESTSApps;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import edu.escuelaing.app.RESTService;
public class ServiceCSS implements RESTService {

    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: css\r\n" +
                "\r\n";
    }

    @Override
    public String getResponse() {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("src/main/resources/archivesPractice/archive2.css")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}

