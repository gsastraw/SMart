package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import se.gu.smart.model.UserAccount;

public final class ViewProfileController extends BaseUserController {

    private UserAccount loggedUser;

    @FXML private Text usernameText;
    @FXML private Text firstNameText;
    @FXML private Text lastNameText;
    @FXML private Text ageText;
    @FXML private Text assignedIssuesText;
    @FXML private TextArea aboutMeTextArea;

    public void initData(UserAccount loggedUser){
        firstNameText.setText(loggedUser.getDisplayName());
        lastNameText.setText(loggedUser.getDisplayName());


    }

}
