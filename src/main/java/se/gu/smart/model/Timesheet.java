package se.gu.smart.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Timesheet {

    private final UserAccount user;
    private final Project project;
    private List<TimesheetEntry> entries;

    public Timesheet(UserAccount user, Project project) {
        this.user = user;
        this.project = project;
    }

    public UserAccount getUser() {
        return user;
    }

    public Project getProject() {
        return project;
    }

    public List<TimesheetEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public List<TimesheetEntry> getEntriesByRange(LocalDateTime startDate, LocalDateTime endDate) {
        return entries.stream()
            .filter(entry -> entry.getStartTime().isAfter(startDate) && entry.getEndTime().isBefore(endDate))
            .collect(toUnmodifiableList());
    }

    public void addEntry(TimesheetEntry entry) {
        entries.add(entry);
    }
}


