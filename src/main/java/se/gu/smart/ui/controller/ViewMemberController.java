package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.model.account.Account;

import java.util.Optional;

public class ViewMemberController extends BaseUserController{
    @FXML
    private final NewProjectAddMembersController newProjectAddMembersController = new NewProjectAddMembersController();

    @FXML
    private Text usernameText;

    @FXML
    private Text displayText;

    @FXML
    private TextArea aboutMeTextArea;

    @FXML
    private Optional<Account> loggedUser = newProjectAddMembersController.getUser();

    @FXML
    public void initialize(){
        viewUser(loggedUser);
    }

    @FXML
    void viewUser(Optional<Account> loggedUser){
        usernameText.setText(String.valueOf(loggedUser.get().getUsername()));
        displayText.setText(String.valueOf(loggedUser.get().getDisplayName()));
        aboutMeTextArea.setText(String.valueOf(loggedUser.get().getBio()));
    }
}
