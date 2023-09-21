package com.example.mppproject;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class EditMemberController {

    @FXML
    public Button btnMemberSave;

    @FXML
    public Button btnMemberCancel;

    @FXML
    private Text messageBar;

    @FXML
    public TextField txtMemberAddrCity;

    @FXML
    public TextField txtMemberAddrState;

    @FXML
    public TextField txtMemberAddrStr;

    @FXML
    public TextField txtMemberAddrZip;

    @FXML
    public TextField txtMemberFName;

    @FXML
    public TextField txtMemberID;

    @FXML
    public TextField txtMemberLName;

    @FXML
    public TextField txtMemberPhone;

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnMemberCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveMember(ActionEvent event) {
        if (isMemberInfoInvalid()) {
            messageBar.setFill(Color.RED);;
            messageBar.setText("Fields can't be empty!");
            return;
        }

        ControllerInterface systemController = SystemController.getInstance();
        HashMap<String, LibraryMember> members = systemController.allMemberMap();
        LibraryMember editedMember = buildLibraryMember();

        // update member map
        members.put(editedMember.getMemberId(), editedMember);
        systemController.saveAllMember(members.values().stream().toList());

        messageBar.setFill(Color.GREEN);;
        messageBar.setText("Saved Member Successfully");
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
