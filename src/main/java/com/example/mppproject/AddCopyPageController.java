package com.example.mppproject;

import business.Book;
import business.BookCopy;
import business.ControllerInterface;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.concurrent.atomic.AtomicReference;

public class AddCopyPageController {
    @FXML
    private TextField isbn;

    @FXML
    private Button lookUpButton;
    @FXML
    private TextField title;
    @FXML
    private TextField maxCheckoutLength;

    @FXML
    private TableView<BookCopyInfo> tableView;

    @FXML
    private TableColumn<BookCopyInfo, String> copyNumColumn;
    @FXML
    private TableColumn<BookCopyInfo, String> isAvailableColumn;
    @FXML
    private Button addCopyButton;

    private ObservableList<BookCopyInfo> data;


    public static class BookCopyInfo {
        private String copyNum;
        private String isAvailable;

        public BookCopyInfo(String copyNum, String isAvailable) {
            this.copyNum = copyNum;
            this.isAvailable = isAvailable;
        }

        public String getCopyNum() {
            return copyNum;
        }

        public String getIsAvailable() {
            return isAvailable;
        }
    }


    public void initialize() {
        ControllerInterface systemController = SystemController.getInstance();
        ObservableList<BookCopyInfo> data = FXCollections.observableArrayList();
        tableView.setItems(data);
        copyNumColumn.setCellValueFactory(new PropertyValueFactory<>("copyNum"));
        isAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        AtomicReference<Book> book = new AtomicReference<>();
        lookUpButton.setOnAction((ActionEvent event) -> {
            book.set(systemController.searchBook(isbn.getText()));
            if (book.get() == null) {
                showAlert("No such ISBN exists!");
                return;
            }
            title.setText(book.get().getTitle());
            maxCheckoutLength.setText(String.valueOf(book.get().getMaxCheckoutLength()));
            data.clear();
            for (BookCopy bookCopy : book.get().getCopies()) {
                data.add(new BookCopyInfo(bookCopy.getCopyNum() + "", String.valueOf(bookCopy.isAvailable())));
            }
        });
        addCopyButton.setOnAction((ActionEvent  e) ->{
            if(book.get() == null){
                showAlert("Invalid book!");
                return;
            }
            book.get().addCopy();
            systemController.saveNewBook(book.get());
            showSuccess("Copy is saved");
            data.clear();
            for (BookCopy bookCopy : book.get().getCopies()) {
                data.add(new BookCopyInfo(bookCopy.getCopyNum() + "", String.valueOf(bookCopy.isAvailable())));
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.showAndWait();
    }
}