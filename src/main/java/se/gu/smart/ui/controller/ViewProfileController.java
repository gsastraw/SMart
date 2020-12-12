package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.model.account.Account;

public final class ViewProfileController extends BaseUserController {

    private Account loggedUser;

    @FXML
    private Text usernameText;
    @FXML
    private Text displayText;
    @FXML
    private Text ageText;
    @FXML
    private Text assignedIssuesText;
    @FXML
    private TextArea aboutMeTextArea;
    @FXML
    private Button editButton;
    @FXML
    void redirectEdit(MouseEvent event) {
        redirect(event, "user_edit_profile");
    }

    public void userData(Account loggedUser){
        usernameText.setText(loggedUser.getDisplayName());
        displayText.setText(loggedUser.getDisplayName());
        //ageText.setText(loggedUser.getAge);
        //assignedIssuesText.setText(loggedUser.getIssues);
        //aboutMeTextArea.setText(loggedUser.getBio);
        //


    }
}
