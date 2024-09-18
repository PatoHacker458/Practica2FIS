package org.example.practica2fis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.practica2fis.Models.Compound;
import org.example.practica2fis.Models.Seat;

public class HelloController {

    @FXML
    private GridPane seatGrid;

    @FXML
    private TextField seatNumberInput;

    @FXML
    private Label seatInfoLabel;

    @FXML
    private Button buyButton;

    private Compound compound;

    @FXML
    public void initialize() {
        // Crear la matriz de asientos (6 filas, 4 columnas)
        compound = new Compound(6, 4);
        drawSeats();
    }

    private void drawSeats() {
        seatGrid.getChildren().clear();
        for (int i = 0; i < compound.seatsMatrix.size(); i++) {
            for (int j = 0; j < compound.seatsMatrix.get(i).size(); j++) {
                Seat seat = compound.seatsMatrix.get(i).get(j);

                // Crear un círculo para cada asiento
                Circle circle = new Circle(20); // Radio del círculo
                circle.setStroke(Color.BLACK);
                circle.setFill(seat.isAvailable() ? Color.CYAN : Color.GRAY);

                // Crear un Label para mostrar el número del asiento
                Label seatLabel = new Label(String.valueOf(seat.getId()));

                // Crear un StackPane para combinar el círculo y el número
                StackPane seatPane = new StackPane();
                seatPane.getChildren().addAll(circle, seatLabel);

                // Asignar el círculo al asiento
                seat.setCircle(circle);

                // Agregar el StackPane al GridPane
                seatGrid.add(seatPane, j, i);
            }
        }

    }

    @FXML
    public void onExitButtonClick() {

    }

    @FXML
    public void onBuyButtonClick() {

    }

}