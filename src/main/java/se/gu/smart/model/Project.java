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
    private Set<ProjectMember> members = new HashSet<>();
    private LocalDate startDate;
    private LocalDate deadline;
    private Set<ProjectIssue> issues = new HashSet<>();

    public Project(String title, String description, LocalDate startDate, LocalDate deadline) {
        this(UUID.randomUUID(), title, description, LocalDate.now(), deadline);
    }

    public Project(UUID projectId, String title, String description, LocalDate startDate, LocalDate deadline) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.startDate = LocalDate.now();
        this.deadline = deadline;
    }

    public boolean addMember(UserAccount userAccount){
        return members.add(new ProjectMember(userAccount));
    }

    public boolean removeMember(ProjectMember projectMember){
        return members.remove(projectMember);
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
