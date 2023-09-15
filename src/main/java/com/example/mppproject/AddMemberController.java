package com.example.mppproject;

import business.Address;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AddMemberController implements Initializable {
    @FXML
    public Button btnMemberSave;
    @FXML
    public Button btnMemberCancel;
    @FXML
    private Text messageBar;
    @FXML
    private TextField txtMemberAddrCity;
    @FXML
    private TextField txtMemberAddrState;
    @FXML
    private TextField txtMemberAddrStr;
    @FXML
    private TextField txtMemberAddrZip;
    @FXML
    private TextField txtMemberFName;
    @FXML
    private TextField txtMemberID;
    @FXML
    private TextField txtMemberLName;
    @FXML
    private TextField txtMemberPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<LibraryMember> members = new DataAccessFacade().getMembers();
        if (members.size() > 0) {
            int lastMemberId = Integer.parseInt(members.get(members.size()-1).getMemberId());
            txtMemberID.setText(String.valueOf(lastMemberId+1));
        } else {
            txtMemberID.setText(String.valueOf(1001));
        }
    }

    @FXML
    void saveMember(ActionEvent event) throws InterruptedException {
        if (isMemberInfoInvalid()) {
            messageBar.setFill(Color.RED);;
            messageBar.setText("Fields can't be empty!");
            return;
        }

        DataAccessFacade dataAccessFacade = new DataAccessFacade();
        dataAccessFacade.saveNewMember(buildLibraryMember());

        messageBar.setFill(Color.GREEN);;
        messageBar.setText("Saved Member Successfully");
    }

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnMemberCancel.getScene().getWindow();
        stage.close();
    }

    private boolean isMemberInfoInvalid() {
        return  txtMemberAddrStr.getText().trim().isEmpty()
            || txtMemberAddrCity.getText().trim().isEmpty()
            || txtMemberAddrState.getText().trim().isEmpty()
            || txtMemberAddrZip.getText().trim().isEmpty()
            || txtMemberID.getText().trim().isEmpty()
            || txtMemberFName.getText().trim().isEmpty()
            || txtMemberLName.getText().trim().isEmpty()
            || txtMemberPhone.getText().trim().isEmpty();
    }

    private LibraryMember buildLibraryMember() {
        Address address = new Address(
            txtMemberAddrStr.getText().trim(),
            txtMemberAddrCity.getText().trim(),
            txtMemberAddrState.getText().trim(),
            txtMemberAddrZip.getText().trim()
        );

        return new LibraryMember(
            txtMemberID.getText().trim(),
            txtMemberFName.getText().trim(),
            txtMemberLName.getText().trim(),
            txtMemberPhone.getText().trim(),
            address
        );
    }

}

