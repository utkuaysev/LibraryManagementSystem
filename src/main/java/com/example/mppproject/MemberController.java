package com.example.mppproject;

import business.LibraryMember;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MemberController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        DataAccessFacade dataAccessFacade = new DataAccessFacade();
        HashMap<String, LibraryMember> memberHashMap = dataAccessFacade.readMemberMap();
        List<LibraryMember> members = new ArrayList<>(memberHashMap.values());
        members.sort(Comparator.comparing(LibraryMember::getMemberId));

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
}
