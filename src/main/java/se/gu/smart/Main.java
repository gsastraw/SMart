package se.gu.smart;

import static java.util.Objects.requireNonNull;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        final var classLoader = getClass().getClassLoader();

        final var login = FXMLLoader.load(requireNonNull(classLoader.getResource("fxml/login.fxml")));

        final var scene = new Scene((Parent) login);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
