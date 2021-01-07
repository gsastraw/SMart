package se.gu.smart.repository;

import se.gu.smart.model.project.timesheet.Timesheet;
import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectMember;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProjectRepository {

    private final Set<Project> projects = new HashSet<>();

    protected ProjectRepository() {
    }

    public Project createProject(String title, String description, LocalDate startDate, LocalDate deadline){
        Project project = new Project(title, description, startDate, deadline);
        projects.add(project);
        return project;
    }

    public Optional<Project> getProject(UUID projectId) {
        return projects.stream().filter(project -> project.getProjectId().equals(projectId)).findAny();
    }

    public Set<Project> getProjectsByUser(UUID userId) {
        return projects.stream()
                .filter(project -> project.getMembers().stream()
                        .anyMatch(projectMember -> projectMember.getAccount().getAccountId().equals(userId)))
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
                if (member.getAccount().getAccountId().equals(userId)) {
                    return member.getTimesheet();
                }
            }
            return null;
        }).collect(Collectors.toSet());
    }

    public Optional<Timesheet> getTimesheetByUserAndProject(UUID userId, UUID projectId) {
        return getTimesheetByUser(userId).stream().findFirst().map(project -> {
            for(ProjectMember member : project.getProject().getMembers()) {
                if(member.getAccount().getAccountId().equals(userId) &&
                        project.getProject().getProjectId().equals(projectId)) {
                    return member.getTimesheet();

                }
            }

            return null;
        });
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
}
