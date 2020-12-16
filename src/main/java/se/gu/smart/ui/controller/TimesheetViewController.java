package se.gu.smart.ui.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private final ObservableList<TimesheetEntry> data = FXCollections.observableArrayList(
            param -> new Observable[] {
                param.activityProperty(),
                param.descriptionProperty(),
                param.startTimeProperty(),
                param.endTimeProperty()
            }
        );

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
        super.initialize();

        startTime.setCellValueFactory (new PropertyValueFactory<>("startTime"));
        endTime.setCellValueFactory (new PropertyValueFactory<>("endTime"));
//        totalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        timesheet.getSelectionModel().getSelectedItems()
            .addListener((ListChangeListener<TimesheetEntry>) c -> {
                // TODO
            });

        data.addListener((ListChangeListener<TimesheetEntry>) change -> {
            while (change.next()) {
                for (TimesheetEntry entry : change.getAddedSubList()) {
                    timesheet.getItems().add(entry);
                }
            }
        });
    }

    @FXML
    void setStartTime() {
        if (!data.isEmpty()) {
            var lastEntry = data.get(data.size() - 1);

            if (lastEntry.getEndTime() == null) {
                return;
            }
        }

        data.add(new TimesheetEntry());
    }

    @FXML
    void setEndTime() {
        if (data.isEmpty()) {
            return;
        }

        var lastEntry = data.get(data.size() - 1);

        if (lastEntry.getEndTime() != null) {
            return;
        }

        lastEntry.setEndTime(LocalDateTime.now());
    }

}
