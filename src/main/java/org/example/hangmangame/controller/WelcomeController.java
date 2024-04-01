package org.example.hangmangame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.hangmangame.view.SecretWordStage;
import org.example.hangmangame.view.WelcomeStage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        SecretWordStage.getInstance();
        WelcomeStage.deleteInstance();
    }
}
