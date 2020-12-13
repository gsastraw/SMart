package se.gu.smart.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import se.gu.smart.model.account.Account;
import se.gu.smart.model.project.Project;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Timesheet {

    private final Account account;
    private final Project project;
    private List<TimesheetEntry> entries;

    public Timesheet(Account account, Project project) {
        this.account = account;
        this.project = project;
    }

    public Account getAccount() {
        return account;
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


