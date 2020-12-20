package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.project.Project;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

public final class UserDashboardController extends BaseUserController {

    private final ProjectRepository projectRepository = Repositories.getProjectRepository();
    private final SessionManager sessionManager = SessionManager.getInstance();

    private Project project;

    @FXML
    private ListView<Project> projectListView;
    @FXML
    private Button projectButton;
    @FXML
    private Label projectLabel;

    @FXML
    public void initialize() {
        super.initialize();

        final var activeSession = sessionManager.getActiveSession();

        if(activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        ObservableSet<Project> projects = FXCollections.observableSet
                (projectRepository.getProjectsByUser(activeSession.get().getAccountId()));

        projectListView.setItems(FXCollections.observableArrayList(projects));
    }

    @FXML
    void redirectAdminDashboard(MouseEvent event) {
        redirect(event, "admin_dashboard");
    }
    @FXML
    void redirectNewProject(MouseEvent event) {
        redirect(event, "user_new_project");
    }
}