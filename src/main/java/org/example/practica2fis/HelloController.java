package org.example.practica2fis;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.practica2fis.Models.Compound;
import org.example.practica2fis.Models.Seat;
import org.example.practica2fis.Models.Validator;

import java.util.Optional;

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
    private Label availableLbl; //hacer visible para mostrar msj de ocupado
    @FXML
    private VBox informationSeat, notAvailableSeat;
    @FXML
    private Label seatNumber, seatCost;

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
                    informationSeat.setVisible(false);
                    notAvailableSeat.setVisible(false);
                }
            }

            if(validator.checkInput(newValue)){
                int index = Integer.parseInt(newValue);
                if(compound.checkAvailability(index)){
                    seatAvailable(index);
                    availableLbl.setVisible(false);
                }else{//not available
                    seatNotAvailable(index);
                    availableLbl.setVisible(true);
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
        Platform.exit();
    }

    @FXML
    public void onBuyButtonClick() {
        // Obtener el número de asiento ingresado
        int seatNumber = Integer.parseInt(seatNumberInput.getText());

        if (showConfirmation("Confirmar compra", "¿Esta seguro que desea comprar este asiento?")){
            compound.buySeat(seatNumber); //Esto ya cambia el color del circulo

            showMessage("Compra Exitosa", "La compra se ha realizado con éxito", Alert.AlertType.INFORMATION);

            if (showConfirmation("Continuar comprando", "¿Desea seguir comprando?")){
                clear();
            }else{
                Platform.exit();
            }
        }else {
            clear();
        }
    }



    private void seatAvailable(int index){
        compound.selectSeat(index);
        seatInfoLabel.setText(compound.seatInfo(index));
        informationSeat.setVisible(true);
    }

    private void seatNotAvailable(int index){
        notAvailableSeat.setVisible(true);
    }

    private void showMessage(String title, String message, Alert.AlertType alertType) {//Method to show an alert
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmation(String title, String message){//Method to show a confirmation message
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(title);
        confirmation.setContentText(message);
        Optional<ButtonType> response = confirmation.showAndWait();
        return (response.get() == ButtonType.OK);
    }

    private void clear(){
        seatNumberInput.clear();
//        seatNumber.setText("");
//        seatCost.setText("");
        notAvailableSeat.setVisible(false);
        informationSeat.setVisible(false);
    }

}