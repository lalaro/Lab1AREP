package edu.escuelaing.app.RESTSApps;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import edu.escuelaing.app.RESTService;

public class ServiceImage implements RESTService {

    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: png\r\n" +
                "\r\n";
    }

    @Override
    public String getResponse() {
        String imagePath = "src/main/resources/archivesPractice/archive4.png";
        File imageFile = new File(imagePath);
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(imageBytes);
    }
}
