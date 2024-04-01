package org.example.hangmangame.view.alert;

import javafx.scene.control.Alert;

public class AlertBox implements IAlertBox {

    @Override
    public void showAlertMessage(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
