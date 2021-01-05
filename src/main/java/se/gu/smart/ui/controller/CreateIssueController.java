package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.IssueRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.TicketRepository;
import se.gu.smart.security.session.SessionManager;

import java.util.Optional;

public class CreateIssueController extends BaseUserController{
    private final SessionManager sessionManager = SessionManager.getInstance();
    private final IssueRepository issueRepository = Repositories.getIssueRepository();

    @FXML
    private TextField issueNameField;

    @FXML
    private TextField issueDescriptionField;

    @FXML
    private TextField issueNumberField;

    @FXML
    private Button createIssueButton;

    @FXML
    public void initialize() {
        super.initialize();

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }
    }

    @FXML
    void onDoneClicked(MouseEvent event) {
        if (issueNameField.getText().isEmpty() || issueDescriptionField.getText().isEmpty()) {
            issueNameField.setPromptText("Please fill this in before submitting a ticket");
            issueDescriptionField.setPromptText("Please fill this in before submitting a ticket");
        } else {
            issueRepository.createIssue(
                    Integer.parseInt(issueNumberField.getText()),
                    issueNameField.getText(),
                    issueDescriptionField.getText(),
                    true
            );
            issueNameField.clear();
            issueDescriptionField.clear();
            issueNumberField.clear();
        }
        System.out.println(issueRepository.getIssues().toString());

    }
}