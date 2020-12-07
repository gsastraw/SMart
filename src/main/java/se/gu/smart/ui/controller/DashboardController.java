package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public final class DashboardController extends BaseController {

    @FXML
    void redirectMessages(MouseEvent event) {
        redirect(event, "user_messages");
    }

    @FXML
    void redirectNewProject(MouseEvent event) {
        redirect(event, "user_new_project");
    }

    @FXML
    void redirectCalender(MouseEvent event) {
        redirect(event, "user_calender");
    }

    @FXML
    void redirectProjects(MouseEvent event) {
        redirect(event, "login");
    }

    @FXML
    void redirectViewProfile(MouseEvent event) {
        redirect(event, "user_view_profile");
    }

    @FXML
    void redirectTimesheet(MouseEvent event) {
        redirect(event, "user_timesheet");
    }

    @FXML
    public void initialize() {

    }
}

