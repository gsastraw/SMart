package se.gu.smart.model.project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import se.gu.smart.model.account.Account;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class ProjectIssue {

    private String issueNumber;
    private String issueType;
    private String issueName;
    private String issueDescription;
    private Status issueStatus;
    private Set<ProjectMember> assignedWorkers = new HashSet<>();

    public enum Status{
        Complete,
        Incomplete
    }

    public ProjectIssue(String issueNumber, String issueType, String issueName, String issueDescription, Boolean issueStatus){
        this.issueNumber = issueNumber;
        this.issueType = issueType;
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.issueStatus = Status.Incomplete;  //true means open, false means closed.
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getIssueName() {
        return issueName;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getIssueDescription() { return issueDescription; }

    public Status getIssueStatus(){
        return issueStatus;
    }

    public void setIssueStatus(Status issueStatus) {
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

    public void setIssueNumber(String issueNumber){
        this.issueNumber = issueNumber;
    }

    public void setIssueType(String issueType){
        this.issueType = issueType;
    }

    public void setIssueName(String issueName){
        this.issueName = issueName;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    @Override
    public String toString() {
        return "issueNumber= " + issueNumber + "\nissueType= " + issueType + "\nissueName= " + issueName + "\nissueStatus= " + issueStatus + ", assignedWorkers=" + assignedWorkers + '}';
    }
}
