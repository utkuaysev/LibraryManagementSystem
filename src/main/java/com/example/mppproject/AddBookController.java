package com.example.mppproject;

import business.Address;
import business.Author;
import business.Book;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    private List<Author> authors = new ArrayList<>();

    @FXML
    private Text messageBar;

    @FXML
    private Button addAuthorButton;

    @FXML
    private TextArea authorBio;

    @FXML
    private TextField authorCity;

    @FXML
    private TextField authorFname;

    @FXML
    private TextField authorLname;

    @FXML
    private TextField authorPhone;

    @FXML
    private TextField authorState;

    @FXML
    private TextField authorStreet;

    @FXML
    private TextField authorZip;

    @FXML
    private Button cancel;

    @FXML
    private ComboBox<String> checkoutDropdown;

    @FXML
    private ComboBox<String> authorsDropdown;

    @FXML
    private TextField isbnField;

    @FXML
    private Button saveBook;

    @FXML
    private TextField titleField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add("21");
        items.add("7");
        checkoutDropdown.setItems(items);
    }

    @FXML
    void createAuthor(ActionEvent event) {
        if (isAuthorInvalid()) {
            messageBar.setFill(Color.RED);;
            messageBar.setText("Invalid Author!");
            return;
        }


        Address address = new Address(
                authorStreet.getText().trim(),
                authorCity.getText().trim(),
                authorState.getText().trim(),
                authorZip.getText().trim());

        Author author = new Author(
                authorFname.getText().trim(),
                authorLname.getText().trim(),
                authorPhone.getText().trim(),
                address,
                authorBio.getText().trim()
        );

        authors.add(author);
        addAuthor();
    }

    private void addAuthor() {
        ObservableList<String> items = FXCollections.observableArrayList();
        authors.forEach(a -> {
            items.add(a.getFirstName()+" "+a.getLastName());
        });
        authorsDropdown.setItems(items);
    }

    @FXML
    void saveBook(ActionEvent event) {
        if (isBookInvalid()) {
            messageBar.setFill(Color.RED);;
            messageBar.setText("Invalid Book!");
            return;
        }
        Book book = buildBook();
        new SystemController().saveNewBook(book);
        authors.clear();
        messageBar.setFill(Color.GREEN);;
        messageBar.setText("Book Saved Successfully");
    }

    private int getCheckoutLength() {
        return Integer.parseInt(
                checkoutDropdown.getSelectionModel().selectedItemProperty().getValue()
        );
    }

    private Book buildBook() {
        return new Book(
                isbnField.getText().trim(),
                titleField.getText().trim(),
                getCheckoutLength(),
                authors
        );
    }

    @FXML
    void onCancelBookClick(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    private boolean isAuthorInvalid() {
        return authorFname.getText().trim().isEmpty() ||
                authorLname.getText().trim().isEmpty();
    }

    private boolean isBookInvalid() {
        return titleField.getText().trim().isEmpty() ||
                isbnField.getText().trim().isEmpty() ||
                authors.size() == 0 ||
                checkoutDropdown.getSelectionModel().isEmpty();
    }

}
