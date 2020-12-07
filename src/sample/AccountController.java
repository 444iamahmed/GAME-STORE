package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountController {

    @FXML
    Button login;

    public void changeSceneToBrowse() throws IOException
    {
        Parent browseParent = FXMLLoader.load(getClass().getResource("Browse.fxml"));
        Scene browseScene = new Scene(browseParent);

        Stage window = (Stage) login.getScene().getWindow();
        window.setScene(browseScene);
    }
}
