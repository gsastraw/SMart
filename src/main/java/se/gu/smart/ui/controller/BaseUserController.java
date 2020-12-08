package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

import java.util.Random;

public class BaseUserController {



    // will switch to another menu.
    protected void redirect(MouseEvent event, String fxmlFile) {
        var dashboardParent = FXMLUtil.loadFxml(fxmlFile);
        var dashboardScene = new Scene((Parent) dashboardParent);

        var window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.centerOnScreen();
        window.show();
    }
    @FXML
    protected Text randomQuoteText;

    @FXML
    protected Text randomQuoteAuthor;

    @FXML
    void randomizeQuotes(){
        String[] quoteTexts = {
                "If opportunity doesn’t knock, build a door.",
                "Carpe Diem",
                "Live Laugh Love",
                "In a gentle way, you can shake the world.",
                "The man who moves a mountain begins by carrying away small stones.",
                "I'm a success today because I had a friend who believed in me and I didn't have the heart to let him down.",
                "Stand for something or you will fall for anything. Today’s mighty oak is yesterday’s nut that held its ground.",
                "Opportunity is missed by most people because it is dressed in overalls and looks like work."};
        String[] quoteAuthors = {
                "Milton Berle",
                "Horatious",
                "Karen",
                "Gandhi",
                "Confucius",
                "Abraham Lincoln",
                "Rosa Parks",
                "Thomas Edison"};

        int x;
        Random rand = new Random();
        x = rand.nextInt(quoteTexts.length);
        randomQuoteText.setText("”" + quoteTexts[x] + "”");
        randomQuoteAuthor.setText("- " + quoteAuthors[x]);
    }

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
    void redirectViewProfile(MouseEvent event) {
        redirect(event, "user_view_profile");
    }

    @FXML
    void redirectDashboard(MouseEvent event) {
        redirect(event, "user_dashboard");
    }

    @FXML
    void redirectTimesheet(MouseEvent event) {
        redirect(event, "user_timesheet");
    }

}
