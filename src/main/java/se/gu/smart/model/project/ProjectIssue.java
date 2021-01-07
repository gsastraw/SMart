package se.gu.smart.model.project;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProjectIssue {

    private int issueNumber;
    private String issueType;
    private String issueName;
    private String issueDescription;
    private Status issueStatus;
    private Set<ProjectMember> assignedWorkers = new HashSet<>();

    public enum Status{
        COMPLETE,
        INCOMPLETE
    }

    public ProjectIssue(int issueNumber, String issueType, String issueName, String issueDescription){
        this(issueNumber, issueType, issueName, issueDescription, Status.INCOMPLETE);
    }

    @JsonCreator
    public ProjectIssue(
        @JsonProperty("issueNumber") int issueNumber,
        @JsonProperty("issueType") String issueType,
        @JsonProperty("issueName") String issueName,
        @JsonProperty("issueDescription") String issueDescription,
        @JsonProperty("issueStatus") Status issueStatus
    ) {
        this.issueNumber = issueNumber;
        this.issueType = issueType;
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.issueStatus = issueStatus;
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

    public void setIssueNumber(int issueNumber){
        this.issueNumber = issueNumber;
    }

    public void setIssueType(String issueType){
        this.issueType = issueType;
    }

    public void setIssueName(String issueName){
        this.issueName = issueName;
    }

    @Override
    public String toString() {
        return "issueNumber= " + issueNumber + "\nissueType= " + issueType + "\nissueName= " + issueName + "\nissueStatus= " + issueStatus + ", assignedWorkers=" + assignedWorkers + '}';
    }
}
