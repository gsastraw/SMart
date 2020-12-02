package se.gu.smart.model;

import static java.util.Objects.requireNonNull;

public class ProjectMember {

    private final Project project;
    private final UserAccount userAccount;
    private final Timesheet timesheet;

    public ProjectMember(Project project, UserAccount userAccount) {
        this.project = requireNonNull(project);
        this.userAccount = requireNonNull(userAccount);
        this.timesheet = new Timesheet(userAccount, project);
    }

    public Project getProject() {
        return project;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }
}

