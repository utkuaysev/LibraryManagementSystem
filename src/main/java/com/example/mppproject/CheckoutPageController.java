package com.example.mppproject;

import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CheckoutPageController {
    @FXML
    private TextField isbn;

    @FXML
    private TextField memberId;

    @FXML
    private TableView<Checkout> tableView;

    @FXML
    private TableColumn <Checkout, String> isbnColumn;
    @FXML
    private TableColumn <Checkout, String> titleColumn;
    @FXML
    private TableColumn <Checkout, String> checkoutDateColumn;
    @FXML
    private TableColumn <Checkout, String> dueDateColumn;

    @FXML
    private Button checkoutButton;

    private ObservableList<Checkout> data;

    private DataAccess da = new DataAccessFacade();

    public static class Checkout {
        private String isbn;
        private String bookTitle;
        private String checkoutDate;
        private String dueDate;

        public Checkout(String isbn, String bookTitle, String checkoutDate, String dueDate) {
            this.isbn = isbn;
            this.bookTitle = bookTitle;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public String getCheckoutDate() {
            return checkoutDate;
        }

        public String getDueDate() {
            return dueDate;
        }
    }

    @FXML
    private void initialize() {
        ObservableList<Checkout> data = FXCollections.observableArrayList();
        tableView.setVisible(true);
        tableView.setItems(data);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        checkoutButton.setOnAction((ActionEvent event) -> {
            String isbnStr = isbn.getText();
            String memberIdStr = memberId.getText();
            LibraryMember member = da.searchMember(memberIdStr);
            Book book = da.searcBook(isbnStr);
            boolean isAvailable = book.isAvailable();
            if (isAvailable) {
                BookCopy bookCopy = book.getNextAvailableCopy();
                bookCopy.changeAvailability();
                int checkoutLength = book.getMaxCheckoutLength();
                member.checkout(bookCopy, LocalDate.now(), LocalDate.now().plusDays(checkoutLength));
                da.saveNewMember(member);
                for (CheckoutRecordEntry cr : member.getCheckoutRecord().getCheckoutRecordEntryList()) {
                    Checkout checkout = new Checkout(cr.getBookCopy().getBook().getIsbn(),
                            cr.getBookCopy().getBook().getTitle(),
                            cr.getCheckoutDate().toString(),
                            cr.getDueDate().toString());
                    data.add(checkout);
                }

            }
        });
    }
}
