package se.gu.smart.ui;

import static java.util.Objects.requireNonNull;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public final class GUIStarter extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        final var classLoader = getClass().getClassLoader();

        final var login = FXMLLoader.load(requireNonNull(classLoader.getResource("fxml/login.fxml")));

        final var scene = new Scene((Parent) login);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(requireNonNull(classLoader.getResourceAsStream("images/logo.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
