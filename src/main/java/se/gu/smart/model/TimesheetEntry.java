package se.gu.smart.model;

import javafx.beans.property.SimpleStringProperty;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimesheetEntry {

    private String activity;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimesheetEntry(String description) {
        this.description = description;
        this.startTime = LocalDateTime.now();

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() { return endTime; }

    public String getActivity() { return activity; }

    public String getDescription() { return description; }

    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public void setActivity(String activity) { this.activity = activity; }

    public Duration calculateWorkTime() {
        if (startTime == null || endTime == null) {
            return Duration.ZERO;
        }

        return Duration.between(startTime, endTime);
    }

//    long toHours = duration.toHours();
//    long toMinutes = duration.minusHours(toHours).toMinutes();
//    use these later on to get the calculated work time into hours and minutes

}
