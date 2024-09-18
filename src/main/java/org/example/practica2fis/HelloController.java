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
import org.example.practica2fis.Models.Validator;

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
    private final Validator validator = new Validator();

    @FXML
    public void initialize() {
        // Crear la matriz de asientos (6 filas, 4 columnas)
        compound = new Compound(6, 4);
        drawSeats();

        //Setting eventlistener for selection
        seatNumberInput.textProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("El texto ha cambiado de: " + oldValue + " a: " + newValue);
            //deselecting seat
            if(validator.checkInput(oldValue)){
                int lastIndex = Integer.parseInt(oldValue);
                if(compound.checkAvailability(lastIndex)){ //only if it was actually selected, if it was bought no need
                    compound.deselectSeat(lastIndex);
                }
            }

            if(validator.checkInput(newValue)){
                int index = Integer.parseInt(newValue);
                if(compound.checkAvailability(index)){
                    seatAvailable(index);
                }else{//not available
                    seatNotAvailable(index);
                    //method to show non availability
                }
            }
        });

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

    private void seatAvailable(int index){
        compound.selectSeat(index);
    }

    private void seatNotAvailable(int index){

    }

}