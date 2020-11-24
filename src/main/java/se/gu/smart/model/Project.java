package se.gu.smart.model;

import se.gu.smart.permission.ProjectPermission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Project {
    // status and issues might be added

    private final UUID projectId;
    private String title;
    private String description;
    private final Set<UserAccount> members = new HashSet<>();
    private final LocalDate startDate;
    private LocalDate deadline;
    private final Map<UUID, List<ProjectPermission>> memberPermissions = new HashMap<>();

    public Project(String title, String description, LocalDate startDate, LocalDate deadline) {
        this(UUID.randomUUID(), title, description, startDate, deadline);
    }

    public Project(UUID projectId, String title, String description, LocalDate startDate, LocalDate deadline) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Set<UserAccount> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public void addMemberPermission(UUID userId, ProjectPermission permission) {
        memberPermissions.putIfAbsent(userId, new ArrayList<>()); // list should be initialized when member is added
        memberPermissions.get(userId).add(permission);
    }
}
