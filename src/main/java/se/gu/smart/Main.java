package se.gu.smart;

import se.gu.smart.service.Services;
import se.gu.smart.service.UserAccountService;
import se.gu.smart.ui.GUIStarter;

public class Main {

    public static void main(String[] args) {
        UserAccountService accService = Services.getUserAccountService();
        accService.createUser("Ed123", "pass");
        accService.createUser("SirMiso", "swag");
        GUIStarter.main(args);
    }
}
