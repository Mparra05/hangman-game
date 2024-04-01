package org.example.hangmangame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.hangmangame.view.alert.AlertBox;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    @FXML
    private AnchorPane gameAnchorPane;

    @FXML
    private TextField letterTextField;

    private GridPane secretWordSpaces;
    private String secretWord;

    @FXML
    public void onHandleButtonValidate(ActionEvent event) throws IOException {
        char letter;

        if (letterTextField.getText().length() == 1) {
            letter = letterTextField.getText().charAt(0);
            if (Character.isLetter(letter)) {
                if (validateLetter(letter)) {
                    showLetter(findPositionLetter(letter));
                    letterTextField.setText(null);
                }
                else {
                    new AlertBox().showAlertMessage("Mensaje de Error", "Letra incorrecta", "La letra no está en la palabra secreta", Alert.AlertType.ERROR);
                    letterTextField.setText(null);
                }
            }
            else {
                new AlertBox().showAlertMessage("Mensaje de Error", "Letra inválida", "El caracter ingresado no es una letra del abecedario", Alert.AlertType.ERROR);
                letterTextField.setText(null);
            }
        }
        else {
            new AlertBox().showAlertMessage("Mensaje de Error", "Entrada inválida", "Debe ingresar solo una letra", Alert.AlertType.ERROR);
            letterTextField.setText(null);
        }

    }

    public boolean validateLetter(char letter) {
        boolean validatedLetter = false;
        String secretWord = getSecretWord();

        for (byte i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) validatedLetter = true;
        }

        return validatedLetter;
    }

    public ArrayList<Byte> findPositionLetter(char letter) {
        ArrayList<Byte> positionsLetter = new ArrayList<Byte>();
        String secretWord = getSecretWord();

        for (byte i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) positionsLetter.add(i);
        }

        return positionsLetter;
    }

    public void showLetter(ArrayList<Byte> positionsLetter) {
        for (Byte index : positionsLetter) {
            secretWordSpaces.getChildren().get(index).setStyle("-fx-text-fill: black");
        }
    }

    public void setSecretWord (String secretWord) {
        this.secretWord = secretWord;

        secretWordSpaces = new GridPane();
        secretWordSpaces.setLayoutX(50);
        secretWordSpaces.setLayoutY(130);
        secretWordSpaces.setHgap(6);

        for (byte i = 0; i < secretWord.length(); i++) {
            TextField letterTextField = new TextField();
            letterTextField.setPrefWidth(30);
            letterTextField.setPrefHeight(30);
            letterTextField.setText(String.valueOf(secretWord.charAt(i)));
            letterTextField.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
            letterTextField.setBackground(Background.EMPTY);
            letterTextField.setStyle("-fx-text-fill: null");
            letterTextField.setAlignment(Pos.CENTER);
            letterTextField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));
            letterTextField.setEditable(false);
            secretWordSpaces.add(letterTextField, i, 0);
        }
        gameAnchorPane.getChildren().addAll(secretWordSpaces);
    }

    public String getSecretWord () {
        return this.secretWord;
    }
}
