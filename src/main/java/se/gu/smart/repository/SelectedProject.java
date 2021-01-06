package se.gu.smart.repository;

import javafx.collections.ObservableSet;
import se.gu.smart.model.project.Project;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SelectedProject {
    private Set<Project> projects = new HashSet<>();

    public void setProject(Project project){
        projects.add(project);
    }

    public void clearProject(){
        projects.clear();
    }

    public Optional<Project> getProject(){
        return projects.stream().findAny();
    }

}
