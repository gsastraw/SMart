package se.gu.smart.ui.util;

import static java.util.Objects.requireNonNull;
import static se.gu.smart.ui.util.Resources.getResource;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public final class FXMLUtil {

    private FXMLUtil() {
    }

    public static <T> T loadFxml(String fxmlName) {
        requireNonNull(fxmlName);

        fxmlName = fxmlName.endsWith(".fxml") ? fxmlName : fxmlName + ".fxml";

        try {
            return FXMLLoader.load(getResource("fxml/" + fxmlName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file (" + fxmlName + ")");
        }
    }
}
