package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedUser;

public class ViewMemberController extends BaseUserController{
    private SelectedUser selectedUser = Repositories.getSelectedUser();

    @FXML
    private Account loggedUser;
    @FXML
    private Text usernameText;
    @FXML
    private Text displayText;
    @FXML
    private TextArea aboutMeTextArea;

    @FXML
    public void initialize(){
        this.loggedUser = selectedUser.getUser().get();
        viewUser();
    }

    @FXML
    void viewUser(){
        usernameText.setText(String.valueOf(loggedUser.getUsername()));
        displayText.setText(String.valueOf(loggedUser.getDisplayName()));
        aboutMeTextArea.setText(String.valueOf(loggedUser.getBio()));
    }

    @FXML
    void onBackPressed(MouseEvent event){
        redirect(event, "user_view_project");
    }
}
