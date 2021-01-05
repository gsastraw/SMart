package se.gu.smart.repository;

import se.gu.smart.model.Ticket;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.project.ProjectIssue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class IssueRepository {

    private final Set<ProjectIssue> issues = new HashSet<>();

    protected IssueRepository() {
    }

    public ProjectIssue createIssue(int issueNumber, String issueType, String issueName, boolean issueStatus) {
        ProjectIssue issue = new ProjectIssue(issueNumber, issueType, issueName, true);
        issues.add(issue);
        return issue;
    }

    public Optional<ProjectIssue> getIssue(int issueNumber) {
        return issues.stream().filter(issue -> issue.getIssueNumber() == (issueNumber)).findAny();
    }

    public Set<ProjectIssue> getIssues() {
        return Collections.unmodifiableSet(issues);
    }

    public boolean removeIssue(int issueNumber) {
        return issues.removeIf(issue -> issue.getIssueNumber() == (issueNumber));
    }

}
