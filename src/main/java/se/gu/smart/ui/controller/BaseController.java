package se.gu.smart.ui.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

import java.util.Random;

public class BaseController {

    protected void redirect(Event event, String fxmlFile) {
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
                "Opportunity is missed by most people because it is dressed in overalls and looks like work.",
                "Ugandan Bruce Lee... they call him... BRUCE U!",
                "When you reach the end of your rope, tie a knot in it and hang on."};
        String[] quoteAuthors = {
                "Milton Berle",
                "Horatious",
                "Karen",
                "Gandhi",
                "Confucius",
                "Abraham Lincoln",
                "Rosa Parks",
                "Thomas Edison",
                "VJ Emmie",
                "Franklin D. Roosevelt"};

        int x;
        Random rand = new Random();
        x = rand.nextInt(quoteTexts.length);
        randomQuoteText.setText("”" + quoteTexts[x] + "”");
        randomQuoteAuthor.setText("- " + quoteAuthors[x]);
    }
}
