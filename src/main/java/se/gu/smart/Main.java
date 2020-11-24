package se.gu.smart;

import se.gu.smart.model.Project;
import se.gu.smart.model.UserAccount;
import se.gu.smart.permission.GeneralPermission;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.UserAccountRepository;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running Main...");
        ProjectRepository projectExample = new ProjectRepository();
        UserAccountRepository test = new UserAccountRepository();
        UserAccount userAccount = test.createUserAccount("Gregory123", "Grog the Stinky");
        userAccount.setPermissions(GeneralPermission.CREATE_PROJECT);
        System.out.println(userAccount.hasPermission(GeneralPermission.CREATE_PROJECT));
        System.out.println(userAccount.listPermissions());

        Project example = projectExample.createProject("Epic Project number 4", "This is a project!",
                LocalDate.of(2020, 4, 23), LocalDate.of(2020, 4, 23));

        example.setDeadline(LocalDate.of(2020, 5, 26));
    }
}
