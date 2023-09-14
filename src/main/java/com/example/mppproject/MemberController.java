package com.example.mppproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberController {

    @FXML
    private Button addMember;

    @FXML
    protected void onAddMemberClick() {
        System.out.println("Add member click");

        try {
            Parent addMemberPage = FXMLLoader.load(AddMemberPage.class.getResource("Addmember.fxml"));
            Scene scene = new Scene(addMemberPage);
            Stage stg = new Stage();
            stg.setTitle("Add Library Member");
            stg.setScene(scene);
            stg.show();
        } catch (IOException ex) {
            System.out.println("Error in opening 'Add Member' page");
        }
    }



}
