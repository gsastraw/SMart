package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.model.UserAccount;

public final class EditProfileController extends BaseUserController {

    private UserAccount loggedUser;

    @FXML
    private Text usernameText;
    @FXML
    private TextField changeName;
    @FXML
    private Text ageText;
    @FXML
    private Text assignedIssuesText;
    @FXML
    private TextArea changeAboutMeTextArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    void redirectBackButton(MouseEvent event) {redirect(event, "user_view_profile");
    }
    @FXML
    void redirectSaveButton(MouseEvent event) {redirect(event, "user_view_profile");
    }

    public void userData(UserAccount loggedUser){
        usernameText.setText(loggedUser.getUsername());
        //ageText.setText(loggedUser.getAge);
        //assignedIssuesText.setText(loggedUser.getIssues);

        //changeName.setText(loggedUser.setDisplayName());
        //changeAboutMeTextArea.setText(loggedUser.setBio());


    }

}
