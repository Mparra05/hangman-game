package org.example.hangmangame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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

        secretWordSpaces = new GridPane();
        secretWordSpaces.setLayoutX(80);
        secretWordSpaces.setLayoutY(100);
        secretWordSpaces.setHgap(6);

        for (byte i = 0; i < getSecretWord().length(); i++) {
            TextField letterTextField = new TextField();
            letterTextField.setPrefWidth(30);
            letterTextField.setPrefHeight(30);
            letterTextField.setEditable(false);
            secretWordSpaces.add(letterTextField, i, 0);
        }
        gameAnchorPane.getChildren().addAll(secretWordSpaces);

        if (letterTextField.getText().length() == 1) {
            letter = letterTextField.getText().charAt(0);
            if (Character.isLetter(letter)) {
                if (validateLetter(letter)) findPositionLetter2(letter);
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

    public ArrayList findPositionLetter(char letter) {
        ArrayList<Byte> positionsLetter = new ArrayList<Byte>();
        String secretWord = getSecretWord();

        for (byte i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) positionsLetter.add(i);
        }

        return positionsLetter;
    }

    public byte[] findPositionLetter2(char letter) {
        byte[] positionsLetter = {};
        /*String secretWord = getSecretWord();

        for (byte i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) positionsLetter[positionsLetter.length] = i;
        }*/
        System.out.println(positionsLetter.length);

        return positionsLetter;
    }

    public void setSecretWord (String secretWord) {
        this.secretWord = secretWord;
    }

    public String getSecretWord () {
        return this.secretWord;
    }
}
