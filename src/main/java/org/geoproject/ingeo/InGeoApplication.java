package org.geoproject.ingeo;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InGeoApplication {
    public static void main(String[] args) {
        Application.launch(GeoJavaFXApp.class, args);
    }
}
