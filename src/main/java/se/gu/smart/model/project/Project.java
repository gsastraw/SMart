package se.gu.smart.model.project;

import static java.util.Objects.requireNonNull;

import se.gu.smart.model.account.Account;
import se.gu.smart.permission.ProjectPermission;

import java.time.LocalDate;
import java.util.*;

public class Project {
    // status and issues might be added

    private final UUID projectId;
    private String title;
    private String description;
    private final LocalDate startDate;
    private LocalDate deadline;
    private final Set<ProjectMember> members = new HashSet<>();
    private final Set<ProjectIssue> issues = new HashSet<>();
    private final Map<UUID, List<ProjectPermission>> memberPermissions = new HashMap<>();

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

    public Set<ProjectMember> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public boolean addMember(Account account) {
        requireNonNull(account);

        memberPermissions.putIfAbsent(account.getAccountId(), new ArrayList<>()); // list should be initialized when member is added
        return members.add(new ProjectMember(this, account));
    }

    public boolean removeMember(UUID userId) {
        requireNonNull(userId);

        memberPermissions.remove(userId);
        return members.removeIf(projectMember -> projectMember.getAccount().getAccountId().equals(userId));
    }

    public void addMemberPermission(UUID userId, ProjectPermission... permissions) {
        if (permissions == null) return;

        Arrays.stream(permissions).filter(Objects::nonNull).forEach(permission -> memberPermissions.get(userId).add(permission));
    }

    public void removeMemberPermission(UUID userId, ProjectPermission permission) {
        memberPermissions.get(userId).remove(permission);
    }

    public Set<ProjectIssue> getIssues() {
        return Collections.unmodifiableSet(issues);
    }

    public void addIssue(ProjectIssue issue) {
        requireNonNull(issue);

        issues.add(issue);
    }

    public void removeIssue(int issueId) {
        issues.removeIf(projectIssue -> projectIssue.getIssueNumber() == issueId);
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
                ", issues=" + issues +
                ", memberPermissions=" + memberPermissions.values();
    }
}
