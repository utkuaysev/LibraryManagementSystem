package com.example.mppproject;

import business.Book;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    private Label Overduemsg;


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
        if (isbn.isEmpty()) {
            showError("Error! isbn Cannot be Empty");
            return;
        }
        searchData.clear();
        SystemController sc = SystemController.getInstance();
        HashMap<String, LibraryMember> records = sc.allMemberMap();

        Book searchedBook = sc.searchBook(isbn);

        if(searchedBook == null) {
            showError("Error! Book not found");
            return;
        }

        for (LibraryMember lm : records.values()) {
            if (lm.getCheckoutRecord() != null) {
                List<CheckoutRecordEntry> a = lm.getCheckoutRecord().getCheckoutRecordEntryList();
                for (CheckoutRecordEntry record : a) {
                    if (record.getBookCopy().getBook().getIsbn().equals(isbn) && record.getDueDate().isBefore(LocalDate.now()) && !searchedBook.isAvailable()) {
                        OverdueInfo br = new OverdueInfo();
                        br.setIsbn(searchedBook.getIsbn());
                        br.setTitle(searchedBook.getTitle());
                        br.setCopyNum(String.valueOf(record.getBookCopy().getCopyNum()));
                        br.setMemberId(lm.getMemberId());
                        br.setName(lm.getFirstName() + " " + lm.getLastName());
                        br.setDueDate(String.valueOf(record.getDueDate()));
                        searchData.add(br);
                    }
                }
            }

        }
        tblOverdueCheckout.setItems(searchData);
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
