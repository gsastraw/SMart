package se.gu.smart;

import se.gu.smart.service.Services;
import se.gu.smart.service.AccountService;
import se.gu.smart.ui.GUIStarter;

public class Main {

    public static void main(String[] args) {
        AccountService accService = Services.getUserAccountService();
        accService.createUser("Ed123", "pass");
        accService.createUser("SirMiso", "swag"); // Temporary stuff
        GUIStarter.main(args);
    }
}
