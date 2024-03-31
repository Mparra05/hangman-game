module org.example.hangmangame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hangmangame to javafx.fxml;
    opens org.example.hangmangame.controller to javafx.fxml;

    exports org.example.hangmangame;
}