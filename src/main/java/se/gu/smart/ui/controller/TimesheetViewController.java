package se.gu.smart.ui.controller;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateTimeStringConverter;
import se.gu.smart.model.TimesheetEntry;
import se.gu.smart.ui.util.TimesheetHolder;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimesheetViewController extends BaseUserController {

    private final ObservableList<TimesheetEntry> data = FXCollections.observableArrayList(
            param -> new Observable[] {
                param.activityProperty(),
                param.descriptionProperty(),
                param.startTimeProperty(),
                param.endTimeProperty()
            }
        );
    private TimesheetHolder timesheetHolder = TimesheetHolder.getInstance();

    @FXML
    private TableView<TimesheetEntry> timesheet;
    @FXML
    private TableColumn<TimesheetEntry, LocalDateTimeStringConverter> startTime;
    @FXML
    private TableColumn<TimesheetEntry, LocalDateTimeStringConverter> endTime;
    @FXML
    private TableColumn<TimesheetEntry, String> totalTime;
    @FXML
    private TableColumn<TimesheetEntry, String> description;
    @FXML
    private TextField descriptionField;

    @FXML
    public void initialize() {
        super.initialize();
        final var selectedTimesheet = timesheetHolder.getTimesheet().get();
        data.addAll(selectedTimesheet.getEntries());

        startTime.setCellValueFactory (new PropertyValueFactory<>("startTime"));
        endTime.setCellValueFactory (new PropertyValueFactory<>("endTime"));
        totalTime.setCellValueFactory(cellData -> {
                TimesheetEntry calculation = cellData.getValue();
                return Bindings.createObjectBinding(
                        calculation::calculateWorkTime);
            });

        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        timesheet.getSelectionModel().getSelectedItems()
            .addListener((ListChangeListener<TimesheetEntry>) c -> {
                // TODO
            });

        data.addListener((ListChangeListener<TimesheetEntry>) change -> {
            while (change.next()) {
                for (TimesheetEntry entry : change.getAddedSubList()) {
                    timesheet.getItems().add(entry);
                    selectedTimesheet.addEntry(entry);
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
        calculateTotalTime();
        timesheet.refresh();
    }

    void calculateTotalTime() {
        if(data.isEmpty()) {
            return;
        }

        var lastEntry = data.get(data.size() - 1);

        if(lastEntry.getStartTime() != null && lastEntry.getEndTime() != null) {
            return;
        }

        assert lastEntry.getStartTime() != null;
        Duration.between(lastEntry.getStartTime(), lastEntry.getEndTime());
    }

    @FXML
    void setNotes() {
        if(data.isEmpty()) {
            return;
        }

        var lastEntry = data.get(data.size() - 1);

        if(lastEntry.getDescription() != null) {
            return;
        }

        lastEntry.setDescription(descriptionField.getText());
        descriptionField.clear();
    }
}
