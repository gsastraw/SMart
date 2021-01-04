package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;

import java.util.Optional;

public class AdminViewProfileController extends BaseAdminController {


    @FXML
    private Optional<Account> loggedUser;

    @FXML
    private Text usernameText;

    @FXML
    private Text displayText;

    @FXML
    private TextArea aboutMeTextArea;

    @FXML
    void redirectEdit(MouseEvent event) {
        redirect(event, "admin_edit_profile");
    }

    @FXML
    public void initialize(){
        super.initialize();

        final var user = new ManageUsersController().getUser();

        if (user.isEmpty()){
            throw new SessionNotFoundException();
        }
        this.loggedUser = user;
        viewUser();
    }

    @FXML
    void viewUser(){
        usernameText.setText(String.valueOf(loggedUser.get().getUsername()));
        displayText.setText(String.valueOf(loggedUser.get().getDisplayName()));
        aboutMeTextArea.setText(String.valueOf(loggedUser.get().getBio()));
    }

}
