package se.gu.smart.repository;

import se.gu.smart.model.Project;
import se.gu.smart.model.ProjectIssue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProjectIssueRepository {
    private final Set<ProjectIssue> projectIssues = new HashSet<>();

    public ProjectIssue createProjectIssue(int issueNumber, String issueType, String issueName) {
        ProjectIssue projectIssue = new ProjectIssue(issueNumber, issueType, issueName);
        projectIssues.add(projectIssue);
        return projectIssue;
    }

    public Optional<ProjectIssue> getProjectIssue(int issueNumber) {
        return projectIssues.stream().filter(ProjectIssue -> ProjectIssue.getIssueNumber() == (issueNumber)).findAny();
    }

    public boolean removeProjectIssue(int issueNumber) {
        return projectIssues.removeIf(ProjectIssue -> ProjectIssue.getIssueNumber() == (issueNumber));
    }

    public void updateProjectIssueNumber(int issueNumber) {
        projectIssues.stream()
                .filter(ProjectIssue -> ProjectIssue.getIssueNumber() == (issueNumber))
                .findAny()
                .ifPresent(userAccount -> userAccount.setIssueNumber(issueNumber));
    }

    public void updateProjectIssueName(int issueNumber, String issueName) {
        projectIssues.stream()
                .filter(ProjectIssue -> ProjectIssue.getIssueNumber() == (issueNumber))
                .findAny()
                .ifPresent(userAccount -> userAccount.setIssueName(issueName));
    }

    public void updateProjectIssueType(int issueNumber, String issueType) {
        projectIssues.stream()
                .filter(ProjectIssue -> ProjectIssue.getIssueNumber() == (issueNumber))
                .findAny()
                .ifPresent(userAccount -> userAccount.setIssueType(issueType));
    }
}
