package com.example.mppproject;


import business.Book;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private TableView<BookTableRecord> tableView;

    @FXML
    private TableColumn<BookTableRecord, String> colAuthors;

    @FXML
    private TableColumn<BookTableRecord, String> colCheckoutLength;

    @FXML
    private TableColumn<BookTableRecord, String> colCopies;

    @FXML
    private TableColumn<BookTableRecord, String> colIsbn;

    @FXML
    private TableColumn<BookTableRecord, String> colTitle;

    @FXML
    private Button addBookButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableView();
    }

    @FXML
    void onAddBookClick(ActionEvent event) {
        try {
            Parent addBookPage = FXMLLoader.load(AddBookPage.class.getResource("AddBookPage.fxml"));
            Scene scene = new Scene(addBookPage);
            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(scene);
            stage.show();

            stage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    // Refresh the parent window here
                    initTableView();
                }
            });
        } catch (IOException ex) {
            System.out.println("Error in opening 'Add Book' page");
            System.out.println(ex);
        }
    }

    @FXML
    void refresh() {
        initTableView();
    }

    public void initTableView() {
        colTitle.setCellValueFactory(new PropertyValueFactory<BookTableRecord, String>("title"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<BookTableRecord, String>("isbn"));
        colAuthors.setCellValueFactory(new PropertyValueFactory<BookTableRecord, String>("authors"));
        colCheckoutLength.setCellValueFactory(new PropertyValueFactory<BookTableRecord, String>("checkoutLength"));
        colCopies.setCellValueFactory(new PropertyValueFactory<BookTableRecord, String>("numCopy"));
        tableView.setItems(getBooks());
    }

    private ObservableList<BookTableRecord> getBooks() {
        ObservableList<BookTableRecord> bookList = FXCollections.observableArrayList();
        List<Book> books = new SystemController().allBooks();

        for (Book book: books) {
            BookTableRecord record = new BookTableRecord();
            record.setTitle(book.getTitle());
            record.setIsbn(book.getIsbn());
            record.setCheckoutLength(String.valueOf(book.getMaxCheckoutLength()));
            record.setNumCopy(String.valueOf(book.getNumCopies()));

            List<String> authors = book.getAuthors()
                    .stream()
                    .map(a -> a.getFirstName()+" "+a.getLastName())
                    .toList();

            record.setAuthors(authors.toString());
            bookList.add(record);
        }
        return bookList;
    }

}
