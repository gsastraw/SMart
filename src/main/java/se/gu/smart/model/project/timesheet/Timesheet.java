package se.gu.smart.model.project.timesheet;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toUnmodifiableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Timesheet {

    private final UUID projectId;
    private final UUID accountId;
    private final List<TimesheetEntry> entries;

    public Timesheet(UUID projectId, UUID accountId) {
        this(projectId, accountId, new ArrayList<>());
    }

    @JsonCreator
    public Timesheet(
        @JsonProperty("projectId") UUID projectId,
        @JsonProperty("accountId") UUID accountId,
        @JsonProperty("entries") List<TimesheetEntry> entries
    ) {
        this.projectId = requireNonNull(projectId);
        this.accountId = requireNonNull(accountId);
        this.entries = requireNonNull(entries);
    }

    public UUID getAccountId() {
        return accountId;
    }

    public UUID getProjectId() {
        return projectId;
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


