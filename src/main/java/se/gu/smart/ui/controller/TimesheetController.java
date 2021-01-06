package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.Timesheet;
import se.gu.smart.model.project.Project;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;
import se.gu.smart.ui.util.FXMLUtil;
import se.gu.smart.ui.util.TimesheetHolder;

import java.util.Optional;

public class TimesheetController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final ProjectRepository projectRepository = Repositories.getProjectRepository();
    private TimesheetHolder timesheetHolder = TimesheetHolder.getInstance();

    @FXML
    private ListView<Project> projectListView;

    @FXML
    public void initialize() {
        super.initialize();

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        projectListView.getSelectionModel().selectedItemProperty().addListener((observableValue, project, t1) -> {
            Optional<Timesheet> timesheet = projectRepository.getTimesheetByUserAndProject
                    (activeSession.get().getAccountId(), t1.getProjectId());
            if (timesheet.isPresent()) {
                timesheetHolder.setTimesheet(timesheet);
                var dashboardParent = FXMLUtil.loadFxml("user_timesheet_view");
                var dashboardScene = new Scene((Parent) dashboardParent);

                var window = (Stage) projectListView.getScene().getWindow();
                window.setScene(dashboardScene);
                window.centerOnScreen();
                window.show();
            }
        });

        ObservableSet<Project> projectTitles = FXCollections.observableSet
                (projectRepository.getProjectsByUser(activeSession.get().getAccountId()));

        projectListView.setItems(FXCollections.observableArrayList(projectTitles));
    }

    @FXML
    void timesheetViewClick(MouseEvent event) {
        redirect(event, "user_timesheet_view");
    }
}
