module org.example.practica2fis {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.practica2fis to javafx.fxml;
    exports org.example.practica2fis;
}