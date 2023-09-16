package com.example.mppproject;

import business.LibraryMember;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;

public class MemberController implements Initializable {
    @FXML
    public Button editMember;
    @FXML
    public Button addMember;
    @FXML
    TableView<MemberInfo> tableView;
    @FXML
    TableColumn<MemberInfo, String> colMemberId;
    @FXML
    TableColumn<MemberInfo, String> colFirstName;
    @FXML
    TableColumn<MemberInfo, String> colLastName;
    @FXML
    TableColumn<MemberInfo, String> colPhone;
    @FXML
    TableColumn<MemberInfo, String> colStreet;
    @FXML
    TableColumn<MemberInfo, String> colCity;
    @FXML
    TableColumn<MemberInfo, String> colState;
    @FXML
    TableColumn<MemberInfo, String> colZip;

    @FXML
    protected void onAddMemberClick() {
        try {
            Parent addMemberPage = FXMLLoader.load(AddMemberPage.class.getResource("AddMember.fxml"));
            Scene scene = new Scene(addMemberPage);
            Stage stage = new Stage();
            stage.setTitle("Add Library Member");
            stage.setScene(scene);
            stage.show();

            stage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    // Refresh the parent window here
                    initTableView();
                }
            });
        } catch (IOException ex) {
            System.out.println("Error in opening 'Add Member' page");
            System.out.println(ex);
        }
    }

    @FXML
    protected void onEditClick() {
        try {
            MemberInfo memberInfo = tableView.getSelectionModel().getSelectedItem();
            if (memberInfo == null) return;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditMember.fxml"));
            Parent root = (Parent) loader.load();

            EditMemberController controller = loader.getController();
            initEditWindow(controller, memberInfo);
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Library Member");
            stage.show();

            stage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    // Refresh the parent window here
                    initTableView();
                }
            });
        } catch (IOException ex) {
            System.out.println("Error in opening 'Edit Member' page");
            System.out.println(ex);
        }
    }

    @FXML
    void refresh() {
        initTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableView();
    }

    public void initTableView() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("memberId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("lastName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("telephone"));
        colStreet.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("street"));
        colCity.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("city"));
        colState.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("state"));
        colZip.setCellValueFactory(new PropertyValueFactory<MemberInfo, String>("zip"));
        tableView.setItems(getMembers());
    }

    private ObservableList<MemberInfo> getMembers() {
        ObservableList<MemberInfo> memberList = FXCollections.observableArrayList();
        List<LibraryMember> members = new SystemController().allMembers();

        // LibraryMember to memberInfo
        for (LibraryMember member: members) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setMemberId(member.getMemberId());
            memberInfo.setFirstName(member.getFirstName());
            memberInfo.setLastName(member.getLastName());
            memberInfo.setTelephone(member.getTelephone());
            memberInfo.setStreet(member.getAddress().getStreet());
            memberInfo.setCity(member.getAddress().getCity());
            memberInfo.setState(member.getAddress().getState());
            memberInfo.setZip(member.getAddress().getZip());
            memberList.add(memberInfo);
        }

        return memberList;
    }

    private void initEditWindow(EditMemberController controller, MemberInfo memberInfo) {
        controller.txtMemberID.setText(memberInfo.getMemberId());
        controller.txtMemberFName.setText(memberInfo.getFirstName());
        controller.txtMemberLName.setText(memberInfo.getLastName());
        controller.txtMemberPhone.setText(memberInfo.getTelephone());
        controller.txtMemberAddrStr.setText(memberInfo.getStreet());
        controller.txtMemberAddrCity.setText(memberInfo.getCity());
        controller.txtMemberAddrState.setText(memberInfo.getState());
        controller.txtMemberAddrZip.setText(memberInfo.getZip());
    }
}
