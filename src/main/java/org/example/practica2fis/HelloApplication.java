package org.example.practica2fis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Conciertos de Luis Miguel");
        stage.setScene(scene);
        stage.show();
        String version = System.getProperty("javafx.runtime.version");
        if (version != null) {
            System.out.println("JavaFX Runtime Version: " + version);
        } else {
            System.out.println("JavaFX not found or version information unavailable.");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}