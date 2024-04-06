package org.example.hangmangame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.hangmangame.model.SecretWord;
import org.example.hangmangame.view.GameStage;
import org.example.hangmangame.view.WelcomeStage;
import org.example.hangmangame.view.alert.AlertBox;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    @FXML
    private AnchorPane gameAnchorPane;
    @FXML
    private Label labelNickname;
    @FXML
    private Label labelAttempts;
    @FXML
    private GridPane gridPaneSecretWordSpaces;
    @FXML
    private TextField validateLetterTextField;
    @FXML
    private Circle head;
    @FXML
    private Circle eye1;
    @FXML
    private Circle eye2;
    @FXML
    private QuadCurve mouth;
    @FXML
    private Line body;
    @FXML
    private Line arm1;
    @FXML
    private Line arm2;
    @FXML
    private Line foot1;
    @FXML
    private Line foot2;

    private GridPane secretWordSpaces, letterUsed;
    private SecretWord secretWord;
    private int attempts = 6, helps = 3;

    @FXML
    public void onHandleButtonValidate(ActionEvent actionEvent) throws IOException {
        String letter;

        if (validateLetterTextField.getText().length() == 1) {
            letter = String.valueOf(validateLetterTextField.getText().charAt(0));
            if (Character.isLetter(letter.charAt(0))) {
                if (validateLetter(letter)) {
                    showLetter(findPositionLetter(letter.toUpperCase()));
                    validateLetterTextField.setText(null);

                    if (validateSecretWordFound()) winGame();
                }
                else {
                    new AlertBox().showAlertMessage("Mensaje de Error", "Letra incorrecta", "La letra no está en la palabra secreta", Alert.AlertType.ERROR);
                    validateLetterTextField.setText(null);
                    showBodyPart(this.attempts--);
                    labelAttempts.setText(String.valueOf(this.attempts));
                    showLetterUsed(letter);
                }
            }
            else {
                new AlertBox().showAlertMessage("Mensaje de Error", "Letra inválida", "El caracter ingresado no es una letra del abecedario", Alert.AlertType.ERROR);
                validateLetterTextField.setText(null);
            }
        }
        else {
            new AlertBox().showAlertMessage("Mensaje de Error", "Entrada inválida", "Debe ingresar solo una letra", Alert.AlertType.ERROR);
            validateLetterTextField.setText(null);
        }

    }

    @FXML
    public void onHandleButtonHelp(ActionEvent actionEvent) {
        if (helps > 0) {
            ArrayList<Byte> lettersNotFound = new ArrayList<Byte>();
            ArrayList<Object> letterHelp = new ArrayList<Object>();

            for (byte i = 0; i < this.secretWord.getSecretWord().length(); i++) {
                if (gridPaneSecretWordSpaces.getChildren().get(i).getStyle().equals("-fx-text-fill: null")) {
                    lettersNotFound.add(i);
                }
            }

            int randomPosition = (int) (Math.random() * lettersNotFound.size());
            letterHelp.add(lettersNotFound.get(randomPosition));
            showLetter(letterHelp);

            this.helps--;
        }
        else new AlertBox().showAlertMessage("Sin ayudas", "Se han agotado tus ayudas.", "Ya has usado tus 3 ayudas en el juego.", Alert.AlertType.INFORMATION);
    }

    @FXML
    public  void onHandleLetterTyped(KeyEvent keyEvent) {
        if (Character.isLetter(keyEvent.getCharacter().charAt(0))) {
            validateLetterTextField.setText(keyEvent.getCharacter().toUpperCase());
        }
        else validateLetterTextField.setText(null);
    }

    public void setSecretWord(SecretWord secretWord, String nickname) {
        this.secretWord = secretWord;
        labelNickname.setText(nickname);
        labelAttempts.setText(String.valueOf(this.attempts));

        letterUsed = new GridPane();
        letterUsed.setLayoutX(144);
        letterUsed.setLayoutY(52);
        letterUsed.setHgap(5);
        gameAnchorPane.getChildren().addAll(letterUsed);

        /*secretWordSpaces = new GridPane();
        secretWordSpaces.setLayoutX(30);
        secretWordSpaces.setLayoutY(150);
        secretWordSpaces.setHgap(6);*/

        for (byte i = 0; i < getSecretWord().getSecretWordSpaces().size(); i++) {
            TextField letterTextField = new TextField();
            letterTextField.setPrefWidth(40);
            letterTextField.setPrefHeight(30);
            letterTextField.setText(getSecretWord().getSecretWordSpaces().get(i));
            letterTextField.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
            letterTextField.setBackground(Background.EMPTY);
            letterTextField.setStyle("-fx-text-fill: null");
            letterTextField.setAlignment(Pos.CENTER);
            letterTextField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));
            letterTextField.setEditable(false);
            if (i <= 9) gridPaneSecretWordSpaces.add(letterTextField, i, 0);
            else gridPaneSecretWordSpaces.add(letterTextField, i-10, 1);
        }
        //gameAnchorPane.getChildren().addAll(secretWordSpaces);
    }

    public SecretWord getSecretWord () {
        return this.secretWord;
    }

    public boolean validateLetter(String letter) {
        boolean validatedLetter = false;

        for (byte i = 0; i < this.secretWord.getSecretWord().length(); i++) {
            if (this.secretWord.getSecretWordSpaces().get(i).equals(letter)) validatedLetter = true;
        }

        return validatedLetter;
    }

    public ArrayList<Object> findPositionLetter(String letter) {
        ArrayList<Object> positionsLetter = new ArrayList<Object>();

        for (byte i = 0; i < this.secretWord.getSecretWord().length(); i++) {
            if (this.secretWord.getSecretWordSpaces().get(i).equals(letter)) positionsLetter.add(i);
        }

        return positionsLetter;
    }

    public void showLetter(ArrayList<Object> positionsLetter) {
        for (Object index : positionsLetter) {
            gridPaneSecretWordSpaces.getChildren().get(index.hashCode()).setStyle("-fx-text-fill: black");
        }
    }

    public void showBodyPart(int attempt) throws IOException{
        switch (attempt) {
            case 6:
                head.setVisible(true);
                eye1.setVisible(true);
                eye2.setVisible(true);
                mouth.setVisible(true);
                break;
            case 5:
                body.setVisible(true);
                break;
            case 4:
                arm1.setVisible(true);
                break;
            case 3:
                arm2.setVisible(true);
                break;
            case 2:
                foot1.setVisible(true);
                break;
            case 1:
                foot2.setVisible(true);
                break;
            case 0:
                loseGame();
                break;
        }
    }

    public void showLetterUsed(String letter) {
        Label labelLetterUsed = new Label();
        labelLetterUsed.setPrefWidth(10);
        labelLetterUsed.setPrefHeight(10);
        labelLetterUsed.setText(letter);
        labelLetterUsed.setFont(Font.font("Algerian", FontWeight.BOLD, 14));
        labelLetterUsed.setStyle("-fx-background-color: blue");
        labelLetterUsed.setStyle("-fx-background-radius: 50");
        labelLetterUsed.setAlignment(Pos.CENTER);
        letterUsed.add(labelLetterUsed, letterUsed.getColumnCount(), 0);
    }

    public void loseGame() throws IOException{
        new AlertBox().showAlertMessage("Game Over", "Game Over", "Has agotado tus intentos, perdiste el juego.", Alert.AlertType.INFORMATION);
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }

    public boolean validateSecretWordFound() {
        boolean secretWordFound = false;
        for (byte i = 0; i < this.secretWord.getSecretWord().length(); i++) {
            if (!gridPaneSecretWordSpaces.getChildren().get(i).getStyle().equals("-fx-text-fill: black")) {
                secretWordFound = false;
                break;
            }
            else secretWordFound = true;
        }

        return secretWordFound;
    }

    public void winGame() throws IOException{
        new AlertBox().showAlertMessage("Felicitaciones", "Felicitaciones", "Has adivinado la palabra secreta '"+getSecretWord().getSecretWord()+"'!!!", Alert.AlertType.INFORMATION);
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }
}