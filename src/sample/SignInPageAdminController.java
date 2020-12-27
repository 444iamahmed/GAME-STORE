package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class SignInPageAdminController extends SignInPageController{

    @Override
    public void changeSceneToMainPage() throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPageAdmin.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);

        Stage window = (Stage) signInButton.getScene().getWindow();
        window.setScene(mainPageScene);
    }


}
