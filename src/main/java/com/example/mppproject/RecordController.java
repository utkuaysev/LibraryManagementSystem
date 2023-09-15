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
    private TableColumn<RecordInfo, String> clmHasReturned;
    @FXML
    private TableColumn<RecordInfo, String> clmReturnedDate;
    @FXML
    private TableColumn<RecordInfo, String> clmFineAmount;
    @FXML
    private Button btnSearchRecord;
    @FXML
    private Button btnPrintRecord;
    @FXML
    private TextField txtRecordMemberId;

    ObservableList<RecordInfo> searchData = FXCollections.observableArrayList();

    public void initialize() {
        clmISBN.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("isbn"));
        clmCopyNum.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("copyNum"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("title"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("memberId"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("name"));
        clmCheckoutDate.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("outDate"));
        clmDueDate.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("dueDate"));
        clmHasReturned.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("hasReturned"));
        clmReturnedDate.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("returnedDate"));
        clmFineAmount.setCellValueFactory(new PropertyValueFactory<RecordInfo, String>("fineAmount"));
    }

    public void searchCheckoutRecords(ActionEvent evt) {
        System.out.println("---Searching CheckoutRecord data");
        String memberID = txtRecordMemberId.getText();
        if (memberID == null || memberID.equals("")) {
            return;
        }
        searchData.clear();

        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> records = da.readMemberMap();

        for (LibraryMember lm : records.values()) {
            if (lm.getCheckoutRecord() != null && lm.getMemberId().equals(memberID)) {
                LibraryMember memberInfo = lm;
                List<CheckoutRecordEntry> a = memberInfo.getCheckoutRecord().getCheckoutRecordEntryList();
                for (CheckoutRecordEntry record : a) {
                    RecordInfo br = new RecordInfo();
                    br.setIsbn(record.getBookCopy().getBook().getIsbn());
                    br.setCopyNum(String.valueOf(record.getBookCopy().getCopyNum()));
                    br.setTitle(record.getBookCopy().getBook().getTitle());
                    br.setMemberId(memberID);
                    br.setName(memberInfo.getFirstName() + " " + memberInfo.getLastName());
                    br.setOutDate(String.valueOf(record.getCheckoutDate()));
                    br.setDueDate(String.valueOf(record.getDueDate()));
                    searchData.add(br);
                }
            }

        }
//        tblCheckOutRecords.getItems().clear();
        tblCheckOutRecords.setItems(searchData);
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

}
