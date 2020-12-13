package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class NewProjectAddMembersController extends BaseUserController{

    @FXML
    private Button backButton;

    @FXML
    public void initialize(){
    }

    @FXML
    void onBackClicked(MouseEvent event){
        redirect(event, "user_new_project");
    }

    @FXML
    void onAddClicked(MouseEvent event){

    }

}
