package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedUser;

import java.util.UUID;

public class AdminEditProfileController extends BaseAdminController{
    private final AccountRepository accountRepository = Repositories.getAccountRepository();
    private SelectedUser selectedUser = Repositories.getSelectedUser();

    @FXML
    private Text usernameText;
    @FXML
    private Account loggedUser;
    @FXML
    private TextField changeName;
    @FXML
    private TextArea changeAboutMeTextArea;

    @FXML
    void redirectBackButton(MouseEvent event) {
        redirect(event, "admin_view_profile");
    }

    @FXML
    public void initialize() {
        this.loggedUser = selectedUser.getUser().get();
        viewUser();
    }

    @FXML
    void redirectSaveButton(MouseEvent event) {
        userData(loggedUser.getAccountId());
        redirect(event, "admin_view_profile");
    }

    public void userData(UUID accountId) {
        if (!changeName.getText().isEmpty() || !changeAboutMeTextArea.getText().isEmpty()){
            accountRepository.updateAccount(accountId, changeName.getText(), changeAboutMeTextArea.getText());
        }
    }

    @FXML
    void viewUser(){
        usernameText.setText(String.valueOf(loggedUser.getUsername()));
    }
}
