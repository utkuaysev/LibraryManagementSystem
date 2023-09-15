package com.example.mppproject;

import business.CheckoutRecordEntry;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class OverdueController {
    @FXML
    public TextField fieldISBN;
    @FXML
    public Button btnSearch;
    @FXML
    public TableView<OverdueInfo> tblOverdueCheckout;

    @FXML
    private TableColumn<OverdueInfo, String> clmISBN;
    @FXML
    private TableColumn<OverdueInfo, String> clmCopyNum;
    @FXML
    private TableColumn<OverdueInfo, String> clmTitle;
    @FXML
    private TableColumn<OverdueInfo, String> clmMemberId;
    @FXML
    private TableColumn<OverdueInfo, String> clmMemberName;
    @FXML
    private TableColumn<OverdueInfo, String> clmDueDate;


    ObservableList<OverdueInfo> searchData = FXCollections.observableArrayList();

    public void initialize() {
        clmISBN.setCellValueFactory(new PropertyValueFactory<OverdueInfo, String>("isbn"));
        clmCopyNum.setCellValueFactory(new PropertyValueFactory<OverdueInfo, String>("copyNum"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<OverdueInfo, String>("title"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<OverdueInfo, String>("memberId"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<OverdueInfo, String>("name"));
        clmDueDate.setCellValueFactory(new PropertyValueFactory<OverdueInfo, String>("dueDate"));

    }

    public void searchOverDueCheckout(ActionEvent actionEvent) {
        System.out.println("--- Searching for Overdue Checkouts");
        String isbn = fieldISBN.getText().trim();
//        if(isbn.isEmpty()) return;

        DataAccess da = new DataAccessFacade();
        HashMap<String, CheckoutRecordEntry> records = da.readCheckoutRecordEntryMap();

        for (CheckoutRecordEntry co : records.values()) {
//            if(co.getDueDate().isBefore(LocalDate.now()) && !co.getBookCopy().getBook().isAvailable()) {
            OverdueInfo overdue = new OverdueInfo();
            overdue.setIsbn("1");
            overdue.setDueDate("1");
            overdue.setCopyNum("1");
            overdue.setMemberId("1");
            overdue.setTitle("1");
            overdue.setName("1");
            searchData.add(overdue);
//            }
        }

        tblOverdueCheckout.setItems(searchData);
    }
}
