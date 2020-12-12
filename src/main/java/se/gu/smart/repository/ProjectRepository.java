package se.gu.smart.repository;

import se.gu.smart.model.project.Project;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ProjectRepository {

    private final Set<Project> projects = new HashSet<>();

    public Project createProject(String title, String description, LocalDate startDate, LocalDate deadline){
        Project project = new Project(title, description, startDate, deadline);
        projects.add(project);
        return project;
    }

    public Optional<Project> getProject(UUID projectId){
        return projects.stream().filter(project -> project.getProjectId().equals(projectId)).findAny();
    }

    public boolean removeProject(UUID projectId){
        return projects.removeIf(project -> project.getProjectId().equals(projectId)); // Returns true if successful at removing
    }

    public void updateProject(UUID projectId, LocalDate deadline){
        projects.stream()
                .filter(project -> project.getProjectId().equals(projectId)) // filters it
                .findAny() // returns it
                .ifPresent(userAccount -> userAccount.setDeadline(deadline)); // sets it
    } // this should be improved to include more things to update
}
