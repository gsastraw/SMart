package se.gu.smart.ui;

import static se.gu.smart.ui.util.Resources.getResourceAsStream;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.gu.smart.storage.StorageProvider;
import se.gu.smart.storage.StorageProviderFactory;
import se.gu.smart.storage.StorageProviderType;
import se.gu.smart.ui.util.FXMLUtil;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class GUIStarter extends Application {

    private final StorageProvider storageProvider = StorageProviderFactory.getStorageProvider(StorageProviderType.FILE);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> periodicPersistenceFuture;

    @Override
    public void init() {
        try {
            storageProvider.load();
            periodicPersistenceFuture = scheduler.scheduleAtFixedRate(this::saveApplicationState, 60, 60, TimeUnit.SECONDS);
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
        saveApplicationState();
        
        if (periodicPersistenceFuture != null) {
            periodicPersistenceFuture.cancel(false);
        }
    }

    private void saveApplicationState() {
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
