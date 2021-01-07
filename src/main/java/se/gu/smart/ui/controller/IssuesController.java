package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectIssue;
import se.gu.smart.model.project.ProjectIssue.Status;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;

public class IssuesController extends BaseUserController {
    private SelectedProject selectedProject = Repositories.getSelectedProject();

    @FXML
    private TableView<ProjectIssue> issueTableView;

    @FXML
    private TableColumn<ProjectIssue, Integer> issueNumber;

    @FXML
    private TableColumn<ProjectIssue, String> issueName;

    @FXML
    private TableColumn<ProjectIssue, String> issueDescription;

    @FXML
    private TableColumn<ProjectIssue, ProjectIssue.Status> issueStatus;

    @FXML
    private TableColumn<ProjectIssue, String> issueType;

    @FXML
    private Button createIssueButton;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteIssueButton;

    @FXML
    private Project desiredProject;

    @FXML
    private Button changeIssueStatusButton;

    @FXML
    public void initialize() {
        super.initialize();

        this.desiredProject = selectedProject.getProject().get();

        issueNumber.setCellValueFactory(new PropertyValueFactory<ProjectIssue, Integer>("issueNumber"));
        issueType.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("issueType"));
        issueName.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("issueName"));
        issueDescription.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("issueDescription"));
        issueStatus.setCellValueFactory(new PropertyValueFactory<ProjectIssue, ProjectIssue.Status>("issueStatus"));

        issueTableView.setEditable(true);
        issueTableView.setItems(FXCollections.observableArrayList(desiredProject.getIssues()));
    }

    @FXML
    void changeStatus(MouseEvent event) {
        ProjectIssue selectedIssue = issueTableView.getSelectionModel().getSelectedItem();
        if (selectedIssue.getIssueStatus() == Status.INCOMPLETE) {
            selectedIssue.setIssueStatus(Status.COMPLETE);
        } else {
         selectedIssue.setIssueStatus(Status.COMPLETE);
        }
        issueTableView.refresh();
    }

    @FXML
    void deleteButtonPressed(MouseEvent event) {
        ProjectIssue selectedIssue = issueTableView.getSelectionModel().getSelectedItem();
        desiredProject.removeIssue(selectedIssue);
        issueTableView.refresh();
        redirect(event, "user_issues"); //I HAVE NO CLUE WHY THIS WORKS IT JUST DOES
                                                //YOU HAVE TO LEAVE THE TABLE FOR IT TO UPDATE
    }

    @FXML
    void redirectNewIssue(MouseEvent event) {
        redirect(event, "user_create_issue");
    }

    @FXML
    void backButtonPressed(MouseEvent event) {
        redirect(event, "user_view_project");
    }
}

