package com.example.mppproject;

import business.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;


public class CheckoutPageController {
    @FXML
    private TextField isbn;

    @FXML
    private TextField memberId;

    @FXML
    private TableView<Checkout> tableView;

    @FXML
    private TableColumn<Checkout, String> isbnColumn;
    @FXML
    private TableColumn<Checkout, String> titleColumn;
    @FXML
    private TableColumn<Checkout, String> checkoutDateColumn;
    @FXML
    private TableColumn<Checkout, String> dueDateColumn;

    @FXML
    private Button checkoutButton;


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
        ControllerInterface systemController = new SystemController();
        ObservableList<Checkout> data = FXCollections.observableArrayList();
        tableView.setVisible(true);
        tableView.setItems(data);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        checkoutButton.setOnAction((ActionEvent event) -> {
            String memberIdStr = memberId.getText();
            String isbnStr = isbn.getText();
            LibraryMember member = systemController.searchMember(memberIdStr);
            Book book = systemController.searchBook(isbnStr);
            if (member == null) {
                showError("No such member exists!");
                return;
            }
            if (book == null) {
                showError("No such ISBN exists!");
                return;
            }
            boolean isAvailable = book.isAvailable();
            if (!isAvailable) {
                showError("Book is not available!");
                return;
            }
            BookCopy bookCopy = book.getNextAvailableCopy();
            bookCopy.changeAvailability();
            int checkoutLength = book.getMaxCheckoutLength();
            member.checkout(bookCopy, LocalDate.now(), LocalDate.now().plusDays(checkoutLength));
            systemController.saveNewMember(member);
            systemController.saveNewBook(book);
            showSuccess("Checkout succesfull");
            data.clear();
            for (CheckoutRecordEntry cr : member.getCheckoutRecord().getCheckoutRecordEntryList()) {
                Checkout checkout = new Checkout(cr.getBookCopy().getBook().getIsbn(),
                        cr.getBookCopy().getBook().getTitle(),
                        cr.getCheckoutDate().toString(),
                        cr.getDueDate().toString());
                data.add(checkout);
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
