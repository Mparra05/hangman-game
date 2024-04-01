package org.example.hangmangame.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.hangmangame.view.GameStage;
import org.example.hangmangame.view.SecretWordStage;
import org.example.hangmangame.view.alert.AlertBox;
import java.io.IOException;

public class SecretWordController {

    @FXML
    private TextField secretWordTextField;

    @FXML
    public void onHandleKeyTextFieldSecretWord(KeyEvent key) {
        /*char keyTyped = key.getCharacter().charAt(0);
        if (Character.isLowerCase(keyTyped) && Character.isLetter(keyTyped)) {

        }*/
    }

    @FXML
    public void onHandleButtonStart(ActionEvent event) throws IOException {
        String secretWord = secretWordTextField.getText().trim();

        for (byte i = 0; i < secretWord.length(); i++) {
            if (!Character.isLetter(secretWord.charAt(i))) {
                new AlertBox().showAlertMessage("Mensaje de Error", "Palabra inválida","Debe contener solo letras", Alert.AlertType.ERROR);
                secretWordTextField.setText(null);
                break;
            }

            if (i == secretWord.length() - 1 && Character.isLetter(secretWord.charAt(i))) {
                GameStage.getInstance().getGameController().setSecretWord(secretWord);
                SecretWordStage.deleteInstance();
            }
        }
    }
}