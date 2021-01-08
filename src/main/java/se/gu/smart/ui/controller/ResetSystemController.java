package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import se.gu.smart.storage.StorageProvider;
import se.gu.smart.storage.StorageProviderFactory;
import se.gu.smart.storage.StorageProviderType;

import java.io.IOException;

public class ResetSystemController extends BaseAdminController  {
    private final StorageProvider storageProvider = StorageProviderFactory.getStorageProvider(StorageProviderType.FILE);

    @FXML
    private Button resetSystemButton;

    @FXML
    private TextField AreYouSureField;

    @FXML
    private Text AreYouSureText;

    @FXML
    private Text caseSensitiveText;

    private boolean warning;

    String extendingText = "The input is case sensitive, make sure it is written in all capital letters";

    @FXML
    void resetSystem(MouseEvent event) throws IOException {

        if(!warning) {
            AreYouSureText.setVisible(true);
            AreYouSureField.setVisible(true);
            warning = true;
        }
        if(AreYouSureField.getText().equals("CONFIRM")) {
            System.out.println("System is reset!");
            deleteStorage(event);
        } else if (AreYouSureField.getText().contains("confirm") || AreYouSureField.getText().contains("Confirm")) {
            caseSensitiveText.setVisible(true);
            extendingText = extendingText + "!";
            caseSensitiveText.setText(extendingText);
            if (caseSensitiveText.getText().contains("!!!!!")) {
                caseSensitiveText.setOpacity(1);
                caseSensitiveText.setFill(Color.rgb(210,39,30));
            }
        }
    }
    void deleteStorage(MouseEvent event) throws IOException {
        logOut(event);
        storageProvider.reset();
    }
}