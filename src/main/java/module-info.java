module org.example.practica2fis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens org.example.practica2fis to javafx.fxml;
    exports org.example.practica2fis;
}