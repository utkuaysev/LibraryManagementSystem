package com.example.mppproject;

import business.SystemController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
    private Tab tabOverdue;
    @FXML
    public Tab tabRecord;


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
        tabOverdue.setDisable(true);
        tabRecord.setDisable(true);
    }

    private void checkAuth() {
        System.out.println("---Current user: " + SystemController.currentAuth);
        switch (SystemController.currentAuth) {
            case LIBRARIAN:
                tabMember.setDisable(true);
                tabBook.setDisable(true);
                tabCheckout.setDisable(false);
                tabAddCopy.setDisable(true);
                tabOverdue.setDisable(true);
                tabRecord.setDisable(false);
                tabPane.getSelectionModel().select(2);
                break;
            case ADMIN:
                tabMember.setDisable(false);
                tabBook.setDisable(false);
                tabCheckout.setDisable(true);
                tabAddCopy.setDisable(false);
                tabOverdue.setDisable(false);
                tabRecord.setDisable(true);
                break;
            case BOTH:
                tabMember.setDisable(false);
                tabBook.setDisable(false);
                tabCheckout.setDisable(false);
                tabAddCopy.setDisable(false);
                tabOverdue.setDisable(false);
                tabRecord.setDisable(false);
                break;
        }
    }
}