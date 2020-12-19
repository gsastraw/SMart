package se.gu.smart.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimesheetEntry {

    private String activity;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private transient final StringProperty activityProperty = new SimpleStringProperty();
    private transient final StringProperty descriptionProperty = new SimpleStringProperty();
    private transient final ObjectProperty<LocalDateTime> startTimeProperty = new SimpleObjectProperty<>();
    private transient final ObjectProperty<LocalDateTime> endTimeProperty = new SimpleObjectProperty<>();

    public TimesheetEntry() {
        var time = LocalDateTime.now();

        this.startTime = time;
        this.startTimeProperty.set(time);
    }

    public String getActivity() {
        return activity;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public StringProperty activityProperty() {
        return activityProperty;
    }

    public StringProperty descriptionProperty() {
        return descriptionProperty;
    }

    public ObjectProperty<LocalDateTime> startTimeProperty() {
        return startTimeProperty;
    }

    public ObjectProperty<LocalDateTime> endTimeProperty() {
        return endTimeProperty;
    }

    public void setActivity(String activity) {
        this.activity = activity;
        this.activityProperty.setValue(activity);
    }

    public void setDescription(String description) {
        this.description = description;
        this.descriptionProperty.setValue(description);
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        this.startTimeProperty.setValue(startTime);
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        this.endTimeProperty.setValue(endTime);
    }

    public String calculateWorkTime() {
        if (startTime == null || endTime == null) {
            return "N/A";
        }

       Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long mins = duration.minusHours(hours).toMinutes();
        return String.format("%02d:%02d", hours, mins);
    }

//    long toHours = duration.toHours();
//    long toMinutes = duration.minusHours(toHours).toMinutes();
//    use these later on to get the calculated work time into hours and minutes

}
