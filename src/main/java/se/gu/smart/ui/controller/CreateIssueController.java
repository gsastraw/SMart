package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectIssue;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;

public class CreateIssueController extends BaseUserController{
    private SelectedProject selectedProject = Repositories.getSelectedProject();


    @FXML
    private TextField issueNameField;

    @FXML
    private TextField issueDescriptionField;

    @FXML
    private TextField issueNumberField;

    @FXML
    private TextField issueTypeField;

    @FXML
    private Project desiredProject;

    @FXML
    private Button createIssueButton;

    @FXML
    void onDoneClicked(MouseEvent event) {
        this.desiredProject = selectedProject.getProject().get();

        if (issueNameField.getText().isEmpty() || issueDescriptionField.getText().isEmpty() || issueNumberField.getText().isEmpty()) {
            issueNameField.setPromptText("Please fill this in before submitting an issue!");
            issueDescriptionField.setPromptText("Please fill this in before submitting an issue!");
            issueNumberField.setPromptText("Please fill this in before submitting an issue!");
            issueTypeField.setPromptText("Please fill thsi in before submitting an issue!");
        } else  if (issueNumberField.getText().matches("[a-zA-Z]+")) {
            issueNumberField.setText("Please fill this with a number!");
        } else {
            ProjectIssue issue = new ProjectIssue(
                    issueNumberField.getText(),
                    issueTypeField.getText(),
                    issueNameField.getText(),
                    issueDescriptionField.getText()
            );
            desiredProject.addIssue(issue);
            redirect(event, "user_issues");
        }
    }
}
