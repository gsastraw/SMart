package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import se.gu.smart.model.Timesheet;
import se.gu.smart.model.TimesheetEntry;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.time.Duration;
import java.time.LocalDate;

public class TimesheetViewController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final ProjectRepository projectRepository = Repositories.getProjectRepository();

    @FXML
    private TableColumn<TimesheetEntry, LocalDate> startTime;
    @FXML
    private TableColumn<TimesheetEntry, LocalDate> endTime;
    @FXML
    private TableColumn<TimesheetEntry, Duration> totalTime;
    @FXML
    private TableColumn<TimesheetEntry, String> description;

    @FXML
    public void initialize() {
        startTime.setCellValueFactory(new PropertyValueFactory<TimesheetEntry, LocalDate>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<TimesheetEntry, LocalDate>("endTime"));
        totalTime.setCellValueFactory(new PropertyValueFactory<TimesheetEntry, Duration>("totalTime"));
        description.setCellValueFactory(new PropertyValueFactory<TimesheetEntry, String>("description"));
    }

}