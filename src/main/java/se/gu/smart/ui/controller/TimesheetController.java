package se.gu.smart.ui.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.exception.TimesheetNotFoundException;
import se.gu.smart.model.Timesheet;
import se.gu.smart.model.project.Project;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;
import se.gu.smart.ui.util.FXMLUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TimesheetController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final ProjectRepository projectRepository = Repositories.getProjectRepository();

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
            Optional<Timesheet> projects = projectRepository.getTimesheetByUserAndProject
                    (activeSession.get().getAccountId(), t1.getProjectId());
            if(projects.isPresent()) {
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

//        projectRepository.getTimesheetByUser(activeSession.get().getAccountId()).forEach(timesheet -> {
//            // TODO: Render timesheets
//        });
    }

    @FXML
    void timesheetViewClick(MouseEvent event) {
        redirect(event, "user_timesheet_view");
    }
}
