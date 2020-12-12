package se.gu.smart;

import se.gu.smart.service.Services;
import se.gu.smart.service.AccountService;
import se.gu.smart.ui.GUIStarter;

public class Main {

    public static void main(String[] args) {
        injectDummyUsers();
        GUIStarter.main(args);
    }

    private static void injectDummyUsers() {
        AccountService accService = Services.getUserAccountService();
        accService.createUser("user", "pass");
        accService.createAdministrator("admin", "pass");
    }
}
