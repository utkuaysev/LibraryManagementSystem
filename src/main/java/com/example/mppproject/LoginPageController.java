package com.example.mppproject;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static business.SystemController.currentAuth;

public class LoginPageController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btn;
    @FXML
    private Text messageBar = new Text();

    public void clear() {
        messageBar.setText("");
    }

    public void login(javafx.event.ActionEvent actionEvent) {
        try {
            String uname = usernameField.getText();
            String password = passwordField.getText();
            ControllerInterface c = new SystemController();
            c.login(uname.trim(), password.trim());
            Parent root = FXMLLoader.load(MainPage.class.getResource("MainPage.fxml"));
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Library Management System");
            stg.setScene(scene);
            stg.show();
        } catch (LoginException | IOException ex) {
            // OptionPane.
            messageBar.setFill(Color.RED);
            messageBar.setText("Error! " + ex.getMessage());

        }
    }
}