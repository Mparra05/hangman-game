package org.example.hangmangame.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {

    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/hangmangame/welcome-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setTitle("Ahorcado");
        getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/hangmangame/images/hangman-icon.png"))));
        setResizable(false);
        setScene(scene);
        show();
    }

    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    public static WelcomeStage getInstance() throws IOException {
        return WelcomeStageHolder.INSTANCE = new WelcomeStage();
    }

    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }
}
