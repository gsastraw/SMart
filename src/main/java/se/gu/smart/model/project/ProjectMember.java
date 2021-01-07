package se.gu.smart.model.project;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.gu.smart.model.project.timesheet.Timesheet;

import java.util.UUID;

public class ProjectMember {

    private final UUID projectId;
    private final UUID accountId;
    private final Timesheet timesheet;

    public ProjectMember(UUID projectId, UUID accountId) {
        this(projectId, accountId, new Timesheet(projectId, accountId));
    }

    @JsonCreator
    public ProjectMember(
        @JsonProperty("projectId") UUID projectId,
        @JsonProperty("accountId") UUID accountId,
        @JsonProperty("timesheet") Timesheet timesheet
    ) {
        this.projectId = requireNonNull(projectId);
        this.accountId = requireNonNull(accountId);
        this.timesheet = requireNonNull(timesheet);
    }

    public UUID getProjectId() {
        return projectId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    @Override
    public String toString() {
        return "ProjectMember" +
                ", account=" + accountId;
    }
}

