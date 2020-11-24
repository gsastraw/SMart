package se.gu.smart.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Project {
    // status and issues might be added

    private final UUID projectId;
    private String title;
    private String description;
    private Set<UserAccount> members = new HashSet<>();
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate deadline;

    public Project(String title, String description, LocalDate startDate, LocalDate deadline) {
        this(UUID.randomUUID(), title, description, LocalDate.now(), deadline);
    }

    public Project(UUID projectId, String title, String description, LocalDate startDate, LocalDate deadline) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.members = members;
        this.startDate = LocalDate.now();
        this.deadline = deadline;
    }
    public UUID getProjectId() {
        return projectId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setDeadline(LocalDate variable) {
        this.deadline = variable;
    }
}
