package org.example.hangmangame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.hangmangame.model.SecretWord;
import org.example.hangmangame.view.GameStage;
import org.example.hangmangame.view.WelcomeStage;
import org.example.hangmangame.view.alert.AlertBox;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private TextField textFieldNickname;
    @FXML
    private TextField textFieldSecretWord;

    @FXML
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        String secretWord = textFieldSecretWord.getText().trim();
        String nickname = textFieldNickname.getText().trim();

        for (byte i = 0; i < secretWord.length();i++) {
            if (!Character.isLetter(secretWord.charAt(i))) {
                new AlertBox().showAlertMessage("Mensaje de Error", "Palabra inválida","Debe contener solo letras", Alert.AlertType.ERROR);
                textFieldSecretWord.setText(null);
                break;
            }

            if (i == secretWord.length() - 1 && Character.isLetter(secretWord.charAt(i))) {
                GameStage.getInstance().getGameController().setSecretWord(new SecretWord(secretWord), nickname);
                WelcomeStage.deleteInstance();
            }
        }
    }

    @FXML
    public  void onHandleNicknameTyped(KeyEvent keyEvent) {
        if (Character.isLetter(keyEvent.getCharacter().charAt(0)) && (keyEvent.getCharacter().charAt(0) >= 'a' || keyEvent.getCharacter().charAt(0) <= 'z')) {
            textFieldNickname.setText(keyEvent.getCharacter().toUpperCase());
        }
    }

    @FXML
    public  void onHandleSecretWordTyped(KeyEvent keyEvent) {
        if (Character.isLetter(keyEvent.getCharacter().charAt(0)) && (keyEvent.getCharacter().charAt(0) >= 'a' || keyEvent.getCharacter().charAt(0) <= 'z')) {
            textFieldSecretWord.setText(keyEvent.getCharacter().toUpperCase());
        }
    }
}
