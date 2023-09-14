package com.example.mppproject;

import business.SystemController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import static business.SystemController.currentAuth;

public class MainPageController {
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabBook;
    @FXML
    private Tab tabMember;
    @FXML
    private Tab tabCheckout;
    @FXML
    private Tab tabAddCopy;



    @FXML
    private Label welcomeText;

    @FXML
    private Button bookButton;

    public void initialize() {
        setAllTabsToDisabled();
        checkAuth();
    }

    private void setAllTabsToDisabled() {
        System.out.println("---Setting All tabs to Disabled");
        tabBook.setDisable(true);
        tabMember.setDisable(true);
        tabCheckout.setDisable(true);
        tabAddCopy.setDisable(true);
    }

    private void checkAuth() {
        System.out.println("---Current user: " + SystemController.currentAuth);
        switch (SystemController.currentAuth) {
            case LIBRARIAN:
                tabMember.setDisable(true);
                tabBook.setDisable(true);
                tabCheckout.setDisable(false);
                tabAddCopy.setDisable(true);
                break;
            case ADMIN:
                tabMember.setDisable(false);
                tabBook.setDisable(false);
                tabCheckout.setDisable(true);
                tabAddCopy.setDisable(false);
                break;
            case BOTH:
                tabMember.setDisable(false);
                tabBook.setDisable(false);
                tabCheckout.setDisable(false);
                tabAddCopy.setDisable(false);
                break;
        }
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");



    }
    @FXML
    protected  void callBlah() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}