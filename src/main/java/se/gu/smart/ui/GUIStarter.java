package se.gu.smart.ui;

import static se.gu.smart.ui.util.Resources.getResourceAsStream;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

public final class GUIStarter extends Application {

    @Override
    public void start(Stage stage) {
        final var login = FXMLUtil.loadFxml("login");

        final var scene = new Scene((Parent) login);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getResourceAsStream("images/logo.png")));
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
