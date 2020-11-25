package se.gu.smart.model;

public class ProjectMember {

    private final UserAccount userAccount;

    private int workTime;

    public ProjectMember(UserAccount userAccount){
        this.userAccount = userAccount;
    }
    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime){
        this.workTime = workTime;
    }

}

