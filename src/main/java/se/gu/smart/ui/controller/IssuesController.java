package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectIssue;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;

public class IssuesController extends BaseUserController {
    private SelectedProject selectedProject = Repositories.getSelectedProject();

    @FXML
    private TableView<ProjectIssue> issueTableView;

    @FXML
    private TableColumn<ProjectIssue, String> issueNumber;

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

        issueNumber.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("issueNumber"));
        issueType.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("issueType"));
        issueName.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("issueName"));
        issueDescription.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("issueDescription"));
        issueStatus.setCellValueFactory(new PropertyValueFactory<ProjectIssue, ProjectIssue.Status>("issueStatus"));

        issueTableView.setEditable(true);
        issueNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        issueType.setCellFactory(TextFieldTableCell.forTableColumn());
        issueDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        issueName.setCellFactory(TextFieldTableCell.forTableColumn());
        issueTableView.setItems(FXCollections.observableArrayList(desiredProject.getIssues()));
    }

    @FXML
    void changeStatus(MouseEvent event) {
        ProjectIssue selectedIssue = issueTableView.getSelectionModel().getSelectedItem();
        if (selectedIssue.getIssueStatus() == ProjectIssue.Status.Incomplete) {
            selectedIssue.setIssueStatus(ProjectIssue.Status.Complete);
        } else {
         selectedIssue.setIssueStatus(ProjectIssue.Status.Incomplete);
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
    void redirectEditIssue(MouseEvent event){redirect(event, "user_edit_issue");}

    @FXML
    void backButtonPressed(MouseEvent event) {
        redirect(event, "user_view_project");
    }

    public void changeNumberCellEvent(TableColumn.CellEditEvent editedcell){
        ProjectIssue issueSelected = issueTableView.getSelectionModel().getSelectedItem();
        issueSelected.setIssueNumber(editedcell.getNewValue().toString());
    }

    public void changeNameCellEvent(TableColumn.CellEditEvent editedcell){
        ProjectIssue issueSelected = issueTableView.getSelectionModel().getSelectedItem();
        issueSelected.setIssueName(editedcell.getNewValue().toString());
    }

    public void changeDescriptionCellEvent(TableColumn.CellEditEvent editedcell){
        ProjectIssue issueSelected = issueTableView.getSelectionModel().getSelectedItem();
        issueSelected.setIssueDescription(editedcell.getNewValue().toString());
    }

    public void changeTypeCellEvent(TableColumn.CellEditEvent editedcell){
        ProjectIssue issueSelected = issueTableView.getSelectionModel().getSelectedItem();
        issueSelected.setIssueType(editedcell.getNewValue().toString());
    }
}

