package se.gu.smart.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class ProjectIssue {

    private int issueNumber;
    private String issueType;
    private String issueName;
    private boolean issueStatus;
    private Set<ProjectMember> assignedWorkers = new HashSet<>();

    public ProjectIssue(int issueNumber, String issueType, String issueName, boolean issueStatus){
        this.issueNumber = issueNumber;
        this.issueType = issueType;
        this.issueName = issueName;
        this.issueStatus = true;  //true means open, false means closed.
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getIssueName() {
        return issueName;
    }

    public String getIssueType() {
        return issueType;
    }

    public boolean getIssueStatus(){
        return issueStatus;
    }

    public void setIssueStatus(boolean issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Set<ProjectMember> getAssignedWorkers() { return Collections.unmodifiableSet(assignedWorkers); }

    public boolean assignWorker(ProjectMember projectMember) {
        requireNonNull(projectMember);

        return assignedWorkers.add(projectMember);
    }

    public boolean unAssignWorker(ProjectMember projectMember) {
        requireNonNull(projectMember);

        return assignedWorkers.remove(projectMember);
    }

    public void setIssueNumber(int issueNumber){
        this.issueNumber = issueNumber;
    }

    public void setIssueType(String issueType){
        this.issueType = issueType;
    }

    public void setIssueName(String issueName){
        this.issueName = issueName;
    }
}
