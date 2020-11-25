package se.gu.smart.model;

public class ProjectIssue {

    private int issueNumber;
    private String issueType;
    private String issueName;

    public ProjectIssue(int issueNumber, String issueType, String issueName){
        this.issueNumber = issueNumber;
        this.issueType = issueType;
        this.issueName = issueName;
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
