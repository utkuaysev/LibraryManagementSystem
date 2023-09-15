package com.example.mppproject;


import business.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, String> colAuthors;

    @FXML
    private TableColumn<Book, String> colCheckoutLength;

    @FXML
    private TableColumn<Book, String> colCopies;

    @FXML
    private TableColumn<Book, String> colIsbn;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private Button addBookButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onAddBookClick(ActionEvent event) {
        System.out.println("Add book clicked");
    }

}
