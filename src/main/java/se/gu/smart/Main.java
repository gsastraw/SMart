package se.gu.smart;

import se.gu.smart.model.project.Project;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.service.Services;
import se.gu.smart.service.AccountService;
import se.gu.smart.ui.GUIStarter;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        injectDummyUsers();
        GUIStarter.main(args);
    }

    private static void injectDummyUsers() {
        AccountService accService = Services.getUserAccountService();
        accService.createAdministrator("admin", "pass");
        accService.createUser("user", "pass");

    }
}
