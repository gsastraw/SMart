package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;

import java.util.Optional;
import java.util.UUID;

public class AdminEditProfileController extends BaseAdminController{
    private final ManageUsersController manageUsersController = new ManageUsersController();
    private final AccountRepository accountRepository = Repositories.getUserAccountRepository();

    @FXML
    private TextField changeName;

    @FXML
    private TextArea changeAboutMeTextArea;

    @FXML
    void redirectBackButton(MouseEvent event) {
        redirect(event, "admin_view_profile");
    }

    private final Optional<Account> user = manageUsersController.getUser();

    @FXML
    void redirectSaveButton(MouseEvent event) {

        userData(user.get().getAccountId());
        redirect(event, "user_view_profile");
    }

    @FXML
    public void initialize() {
    }

    public void userData(UUID accountId) {
        accountRepository.updateAccount(accountId, changeName.getText(), changeAboutMeTextArea.getText());
    }
}



