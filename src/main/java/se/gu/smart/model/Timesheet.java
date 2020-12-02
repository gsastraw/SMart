package se.gu.smart.model;

import java.util.Collections;
import java.util.List;

public class Timesheet {

    private final UserAccount user;
    private final Project project;
    private List<TimesheetEntry> entries;

    public Timesheet(UserAccount user, Project project, TimesheetEntry[] entries) {
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

    public List<TimesheetEntry> getEntriesByRange() {

    }

    public void addEntry(TimesheetEntry entry) {
        entries.add(entry);
    }
}


