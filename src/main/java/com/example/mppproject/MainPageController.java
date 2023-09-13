package com.example.mppproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainPageController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button bookButton;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");



    }
    @FXML
    protected  void callBlah() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}