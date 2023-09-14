package com.example.mppproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddMemberPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AddMemberPage.class.getResource("AddMember.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Library Member");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
