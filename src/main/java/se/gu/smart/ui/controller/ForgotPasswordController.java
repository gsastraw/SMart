package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ForgotPasswordController extends BaseController {

    @FXML
    private Button backToSignInButton;

    @FXML
    void redirectLogIn(MouseEvent event){
        redirect(event, "login");
    }
}
