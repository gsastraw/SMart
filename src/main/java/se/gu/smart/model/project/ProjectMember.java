package se.gu.smart.model.project;

import static java.util.Objects.requireNonNull;

import se.gu.smart.model.Timesheet;
import se.gu.smart.model.account.Account;

public class ProjectMember {

    private final Project project;
    private final Account account;
    private final Timesheet timesheet;

    public ProjectMember(Project project, Account account) {
        this.project = requireNonNull(project);
        this.account = requireNonNull(account);
        this.timesheet = new Timesheet(account, project);
    }

    public Project getProject() {
        return project;
    }

    public Account getAccount() {
        return account;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }
}

