package se.gu.smart.model.project;

import static java.util.Objects.requireNonNull;

import se.gu.smart.model.account.Account;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Project {

    private final UUID projectId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate deadline;
    private final Set<ProjectMember> members = new HashSet<>();
    private final Set<ProjectIssue> issues = new HashSet<>();

    public Project(String title, String description, LocalDate startDate, LocalDate deadline) {
        this(UUID.randomUUID(), title, description, startDate, deadline);
    }

    public Project(UUID projectId, String title, String description, LocalDate startDate, LocalDate deadline) {
        this.projectId = requireNonNull(projectId);
        this.title = title;
        this.description = description;
        this.startDate = requireNonNull(startDate);
        this.deadline = requireNonNull(deadline);
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

    public void setStartDate(LocalDate startDate){this.startDate = startDate;}

    public Set<ProjectMember> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public boolean addMember(Account account) {
        requireNonNull(account);

        return members.add(new ProjectMember(this, account));
    }

    public boolean removeMember(UUID userId) {
        requireNonNull(userId);

        return members.removeIf(projectMember -> projectMember.getAccount().getAccountId().equals(userId));
    }

    public Set<ProjectIssue> getIssues() {
        return Collections.unmodifiableSet(issues);
    }

    public void addIssue(ProjectIssue issue) {
        requireNonNull(issue);

        issues.add(issue);
    }

    public void removeIssue(ProjectIssue issue) {
        issues.remove(issue);
    }

    @Override
    public String toString() {
        return "Project ID: " + projectId + "\nProject Title: " + title;
    }

    public String toString(Account account) {
        return "Project: " +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", members=" + members +
                ", issues=" + issues;
    }
}
