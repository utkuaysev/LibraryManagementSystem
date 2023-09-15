package com.example.mppproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCopyPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AddMemberPage.class.getResource("AddCopyPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Copy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
