package se.gu.smart.model.project;

import se.gu.smart.model.account.Account;

public class ProjectMember {

    private final Account account;
    private int workTime;

    public ProjectMember(Account account){
        this.account = account;
    }

    public Account getUserAccount() {
        return account;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime){
        this.workTime = workTime;
    }

}

