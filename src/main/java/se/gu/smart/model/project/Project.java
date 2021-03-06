package se.gu.smart.model.project;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.gu.smart.model.account.Account;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Project {

    private final UUID projectId;
    private UUID ownerId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate deadline;
    private final Set<ProjectMember> members;
    private final Set<ProjectIssue> issues;

    public Project(UUID ownerId, String title, String description, LocalDate startDate, LocalDate deadline) {
        this(UUID.randomUUID(), ownerId, title, description, startDate, deadline);
        this.members.add(new ProjectMember(projectId, ownerId));
    }

    public Project(UUID projectId, UUID ownerId, String title, String description, LocalDate startDate, LocalDate deadline) {
        this(projectId, ownerId, title, description, startDate, deadline, new HashSet<>(), new HashSet<>());
    }

    @JsonCreator
    public Project(
        @JsonProperty("projectId") UUID projectId,
        @JsonProperty("ownerId") UUID ownerId,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("startDate") LocalDate startDate,
        @JsonProperty("deadline") LocalDate deadline,
        @JsonProperty("members") Set<ProjectMember> members,
        @JsonProperty("issues") Set<ProjectIssue> issues
    ) {
        this.projectId = requireNonNull(projectId);
        this.ownerId = requireNonNull(ownerId);
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.members = requireNonNull(members);
        this.issues = requireNonNull(issues);
    }

    public UUID getProjectId() {
        return projectId;
    }

    public UUID getOwnerId() {
        return ownerId;
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

         if (members.stream().anyMatch(projectMember -> projectMember.getAccountId().equals(account.getAccountId()))) {
             return false;
         }

        return members.add(new ProjectMember(projectId, account.getAccountId()));
    }

    public boolean removeMember(UUID userId) {
        requireNonNull(userId);

        return members.removeIf(projectMember -> projectMember.getAccountId().equals(userId));
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
