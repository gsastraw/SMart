package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

public class TimesheetController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final ProjectRepository projectRepository = Repositories.getProjectRepository();

    @FXML
    private VBox timesheetEntries;

    @FXML
    public void initialize() {
        super.initialize();

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }


        projectRepository.getTimesheetByUser(activeSession.get().getAccountId()).forEach(timesheet -> {
            // TODO: Render timesheets
        });
    }
}
