package org.example.hangmangame;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hangmangame.view.WelcomeStage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }
}
