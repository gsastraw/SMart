package se.gu.smart.repository;

import static java.util.Objects.requireNonNull;

import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectMember;
import se.gu.smart.model.project.timesheet.Timesheet;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProjectRepository implements Repository<Project> {

    private final Set<Project> projects = new HashSet<>();

    protected ProjectRepository() {
    }

    public Project createProject(UUID ownerId, String title, String description, LocalDate startDate, LocalDate deadline){
        final var project = new Project(ownerId, title, description, startDate, deadline);
        projects.add(project);
        return project;
    }

    public Optional<Project> getProject(UUID projectId) {
        return projects.stream().filter(project -> project.getProjectId().equals(projectId)).findAny();
    }

    public Set<Project> getProjectsByUser(UUID userId) {
        return projects.stream()
                .filter(project -> project.getMembers().stream()
                        .anyMatch(projectMember -> projectMember.getAccountId().equals(userId)))
                .collect(Collectors.toSet());
    }

    public Set<Project> getProjectsByRange(LocalDate startDate, LocalDate deadline) {
        return projects.stream()
                .filter(project-> project.getStartDate().isAfter(startDate) && project.getDeadline().isBefore(deadline))
                .collect(Collectors.toSet());
    }

    public Set<Timesheet> getTimesheetByUser(UUID userId) {
        return getProjectsByUser(userId).stream().map(project -> {
            for (ProjectMember member : project.getMembers()) {
                if (member.getAccountId().equals(userId)) {
                    return member.getTimesheet();
                }
            }
            return null;
        }).collect(Collectors.toSet());
    }

    public Optional<Timesheet> getTimesheetByUserAndProject(UUID userId, UUID projectId) {
        return getProject(projectId).orElseThrow().getMembers()
            .stream()
            .filter(projectMember -> projectMember.getAccountId().equals(userId))
            .map(ProjectMember::getTimesheet)
            .findAny();
    }

    public boolean removeProject(UUID projectId) {
        return projects.removeIf(project -> project.getProjectId().equals(projectId)); // Returns true if successful at removing
    }

    public void updateProject(UUID projectId, LocalDate deadline) {
        projects.stream()
                .filter(project -> project.getProjectId().equals(projectId)) // filters it
                .findAny() // returns it
                .ifPresent(userAccount -> userAccount.setDeadline(deadline)); // sets it
    } // this should be improved to include more things to update

    @Override
    public Set<Project> getAll() {
        return Collections.unmodifiableSet(projects);
    }

    @Override
    public void load(Collection<Project> collection) {
        requireNonNull(collection);

        projects.addAll(collection);
    }

    @Override
    public void clear() {
        projects.clear();
    }
}
