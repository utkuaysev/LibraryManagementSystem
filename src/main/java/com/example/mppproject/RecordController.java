package com.example.mppproject;

import business.BookRecords;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class RecordController {
    @FXML
    private TableView<BookRecords> tblCheckOutRecords;
    @FXML
    private TableColumn<BookRecords, String> clmISBN;
    @FXML
    private TableColumn<BookRecords, String> clmCopyNum;
    @FXML
    private TableColumn<BookRecords, String> clmTitle;
    @FXML
    private TableColumn<BookRecords, String> clmMemberId;
    @FXML
    private TableColumn<BookRecords, String> clmMemberName;
    @FXML
    private TableColumn<BookRecords, String> clmCheckoutDate;
    @FXML
    private TableColumn<BookRecords, String> clmDueDate;
    @FXML
    private TableColumn<BookRecords, String> clmHasReturned;
    @FXML
    private TableColumn<BookRecords, String> clmReturnedDate;
    @FXML
    private TableColumn<BookRecords, String> clmFineAmount;
    @FXML
    private Button btnSearchRecord;
    @FXML
    private Button btnPrintRecord;

    ObservableList<BookRecords> searchData = FXCollections.observableArrayList();


    public void searchCheckoutRecords(ActionEvent evt) {
        System.out.println("---Searching Checkout data");
        String memberID = btnSearchRecord.getText();
        if (memberID == null || memberID.equals("")) {
            return;
        }
        // To Do search for Data
        tblCheckOutRecords.getItems().clear();
        tblCheckOutRecords.setItems(searchData);
    }

    public void printCheckoutRecords(ActionEvent evt) {
        System.out.println("---Printing Checkout data");
        for (BookRecords br : searchData)
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
