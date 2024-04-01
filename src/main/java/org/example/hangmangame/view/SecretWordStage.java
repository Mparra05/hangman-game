package org.example.hangmangame.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.hangmangame.controller.SecretWordController;

import java.io.IOException;

public class SecretWordStage extends Stage {

    public SecretWordStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/hangmangame/secret-word-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setTitle("Ahorcado");
        getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/hangmangame/images/hangman-icon.png"))));
        setResizable(false);
        setScene(scene);
        show();
    }

    private static class SecretWordStageHolder {
        private static SecretWordStage INSTANCE;
    }

    public static SecretWordStage getInstance() throws IOException {
        return SecretWordStageHolder.INSTANCE = new SecretWordStage();
    }

    public static void deleteInstance() {
        SecretWordStageHolder.INSTANCE.close();
        SecretWordStageHolder.INSTANCE = null;
    }
}
