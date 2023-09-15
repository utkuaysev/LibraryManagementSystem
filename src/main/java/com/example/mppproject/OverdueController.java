package com.example.mppproject;

import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.SystemController;
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
        if(isbn.isEmpty()) return;

        SystemController sc = new SystemController();
        HashMap<String, LibraryMember> records = sc.allMemberMap();

        for (LibraryMember lm : records.values()) {
            if (lm.getCheckoutRecord() != null) {
                LibraryMember memberInfo = lm;
                List<CheckoutRecordEntry> a = memberInfo.getCheckoutRecord().getCheckoutRecordEntryList();
                for (CheckoutRecordEntry record : a) {
                    if (record.getBookCopy().getBook().getIsbn().equals(isbn) && record.getDueDate().isBefore(LocalDate.now()) && !record.getBookCopy().getBook().isAvailable()) {
                        OverdueInfo br = new OverdueInfo();
                        br.setIsbn(record.getBookCopy().getBook().getIsbn());
                        br.setTitle(record.getBookCopy().getBook().getTitle());
                        br.setCopyNum(String.valueOf(record.getBookCopy().getCopyNum()));
                        br.setMemberId(isbn);
                        br.setName(memberInfo.getFirstName() + " " + memberInfo.getLastName());
                        br.setDueDate(String.valueOf(record.getDueDate()));
                        searchData.add(br);
                    }
                }
            }

        }
        tblOverdueCheckout.setItems(searchData);
    }
}
