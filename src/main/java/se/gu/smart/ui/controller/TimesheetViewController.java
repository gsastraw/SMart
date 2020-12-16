package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.LocalDateTimeStringConverter;
import se.gu.smart.model.TimesheetEntry;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimesheetViewController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final ProjectRepository projectRepository = Repositories.getProjectRepository();
    private TimesheetEntry timesheetEntry;
    private final ObservableList<TimesheetEntry> data = FXCollections.observableArrayList();

    @FXML
    private Button checkIn;
    @FXML
    private Button checkOut;
    @FXML
    private TableView<TimesheetEntry> timesheet;
    @FXML
    private TableColumn<TimesheetEntry, LocalDateTimeStringConverter> startTime;
    @FXML
    private TableColumn<TimesheetEntry, LocalDateTimeStringConverter> endTime;
    @FXML
    private TableColumn<TimesheetEntry, Duration> totalTime;
    @FXML
    private TableColumn<TimesheetEntry, String> description;

    @FXML
    public void initialize() {
        startTime.setCellValueFactory (
                new PropertyValueFactory<TimesheetEntry, LocalDateTimeStringConverter>("startTime"));
        endTime.setCellValueFactory (
                new PropertyValueFactory<TimesheetEntry, LocalDateTimeStringConverter>("endTime"));
        totalTime.setCellValueFactory(new PropertyValueFactory<TimesheetEntry, Duration>("totalTime"));
        description.setCellValueFactory(new PropertyValueFactory<TimesheetEntry, String>("description"));

//        timesheet.getColumns().addAll(startTime, endTime, totalTime, description);

        data.addListener((ListChangeListener<TimesheetEntry>) change -> {
            while (change.next()) {
                for (TimesheetEntry entry : change.getAddedSubList()) {
                    timesheet.getItems().add(entry);
                }
            }
        });
    }

    @FXML
    void setStartTime(MouseEvent event) {
        TimesheetEntry timesheetEntry = new TimesheetEntry("poopy");
        data.get(data.size() - 1);
        if(event.isPrimaryButtonDown()) {
            timesheetEntry.setStartTime(LocalDateTime.now());
        }
    }

}