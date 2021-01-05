package se.gu.smart.ui;

import static se.gu.smart.ui.util.Resources.getResourceAsStream;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.gu.smart.storage.StorageProviderFactory;
import se.gu.smart.storage.StorageProviderType;
import se.gu.smart.ui.util.FXMLUtil;

import java.io.IOException;

public final class GUIStarter extends Application {

    @Override
    public void init() {
        final var storageProvider = StorageProviderFactory.getStorageProvider(StorageProviderType.FILE);
        try {
            storageProvider.load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application state");
        }
    }

    @Override
    public void start(Stage stage) {
        final var login = FXMLUtil.loadFxml("login");

        final var scene = new Scene((Parent) login);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getResourceAsStream("images/logo.png")));
        stage.setOnCloseRequest(this::onCloseCallback);
        stage.show();
    }

    private void onCloseCallback(WindowEvent windowEvent) {
        final var storageProvider = StorageProviderFactory.getStorageProvider(StorageProviderType.FILE);
        try {
            storageProvider.save();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save application state");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
