package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.Ticket;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.project.ProjectIssue;
import se.gu.smart.repository.IssueRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.TicketRepository;

import java.time.LocalDate;

public class IssuesController extends BaseUserController {
    private final IssueRepository issueRepository = Repositories.getIssueRepository();
    private final ObservableList<ProjectIssue> issues = FXCollections.observableArrayList(issueRepository.getIssues());


    @FXML
    private TableView<ProjectIssue> issueTableView;

    @FXML
    private TableColumn<ProjectIssue, Integer> issueNumber;

    @FXML
    private TableColumn<ProjectIssue, String> issueName;

    @FXML
    private TableColumn<ProjectIssue, String> issueDescription;

    @FXML
    private TableColumn<ProjectIssue, Boolean> status;

    @FXML
    private Button createIssueButton;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteIssueButton;

    @FXML
    private Button changeIssueStatusButton;

    @FXML
    public void initialize() {
        super.initialize();

        issueNumber.setCellValueFactory(new PropertyValueFactory<ProjectIssue, Integer>("Number"));
        issueName.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("Name"));
        issueDescription.setCellValueFactory(new PropertyValueFactory<ProjectIssue, String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<ProjectIssue, Boolean>("status"));

        issueTableView.setEditable(true);
        issueTableView.setItems(issues);
    }

    @FXML
    void changeStatus(MouseEvent event) {
        ProjectIssue selectedIssue = issueTableView.getSelectionModel().getSelectedItem();
        selectedIssue.setIssueStatus(false);
        issueTableView.refresh();
    }

    @FXML
    void deleteButtonPressed(MouseEvent event) {
        ProjectIssue selectedIssue = issueTableView.getSelectionModel().getSelectedItem();
        issues.remove(selectedIssue);
    }

    @FXML
    void redirectNewIssue(MouseEvent event) {
        redirect(event, "user_create_issue");
    }

    @FXML
    void backButtonPressed(MouseEvent event) {
        redirect(event, "user_issues");
    }
}

