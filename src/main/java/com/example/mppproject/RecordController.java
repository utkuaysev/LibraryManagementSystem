package com.example.mppproject;

import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.List;

import javafx.scene.control.cell.PropertyValueFactory;

public class RecordController {
    @FXML
    private TableView<RecordInfo> tblCheckOutRecords;
    @FXML
    private TableColumn<RecordInfo, String> clmISBN;
    @FXML
    private TableColumn<RecordInfo, String> clmCopyNum;
    @FXML
    private TableColumn<RecordInfo, String> clmTitle;
    @FXML
    private TableColumn<RecordInfo, String> clmMemberId;
    @FXML
    private TableColumn<RecordInfo, String> clmMemberName;
    @FXML
    private TableColumn<RecordInfo, String> clmCheckoutDate;
    @FXML
    private TableColumn<RecordInfo, String> clmDueDate;
    @FXML
    private Button btnSearchRecord;
    @FXML
    private Button btnPrintRecord;
    @FXML
    private TextField txtRecordMemberId;

    ObservableList<RecordInfo> searchData = FXCollections.observableArrayList();

    public void initialize() {
        btnPrintRecord.setDisable(true);
        clmISBN.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("isbn"));
        clmCopyNum.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("copyNum"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("title"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("memberId"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("name"));
        clmCheckoutDate.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("outDate"));
        clmDueDate.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("dueDate"));
    }

    public void searchCheckoutRecords(ActionEvent evt) {
        System.out.println("---Searching CheckoutRecord data");
        String memberID = txtRecordMemberId.getText();
        if (memberID == null || memberID.equals("")) {
            btnPrintRecord.setDisable(true);
            tblCheckOutRecords.setItems(null);
            showError("Error! MemberId cannot be empty");
            return;
        }
        searchData.clear();
        SystemController sc = SystemController.getInstance();
        HashMap<String, LibraryMember> records = sc.allMemberMap();

        LibraryMember lm = records.get(memberID);

        if(lm == null) {
            btnPrintRecord.setDisable(true);
            tblCheckOutRecords.setItems(null);
            showError("Error! Member not found");
            return;
        }

            if (lm.getCheckoutRecord() != null && lm.getMemberId().equals(memberID)) {
                List<CheckoutRecordEntry> a = lm.getCheckoutRecord().getCheckoutRecordEntryList();
                for (CheckoutRecordEntry record : a) {
                    RecordInfo br = new RecordInfo();
                    br.setIsbn(record.getBookCopy().getBook().getIsbn());
                    br.setCopyNum(String.valueOf(record.getBookCopy().getCopyNum()));
                    br.setTitle(record.getBookCopy().getBook().getTitle());
                    br.setMemberId(memberID);
                    br.setName(lm.getFirstName() + " " + lm.getLastName());
                    br.setOutDate(String.valueOf(record.getCheckoutDate()));
                    br.setDueDate(String.valueOf(record.getDueDate()));
                    searchData.add(br);
                }
            }
        tblCheckOutRecords.setItems(searchData);
        btnPrintRecord.setDisable(false);
    }

    public void printCheckoutRecords(ActionEvent evt) {
        System.out.println("---Printing Checkout data");
        for (RecordInfo br : searchData)
            System.out.println(br);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successfully printed");
        alert.setHeaderText("Successfully printed");
        alert.setContentText("Checkout Records for the searched member is complete. Please check your console.");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
            }
        });
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.showAndWait();
    }


}
