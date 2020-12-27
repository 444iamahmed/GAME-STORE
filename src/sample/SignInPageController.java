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

public abstract class SignInPageController {
    @FXML
    Button signInButton;

    @FXML
    TextField idText;
    @FXML
    PasswordField passwordText;

    @FXML
    Label signInErrorLabel;

    Store myStore;
    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void validateCredentials() throws IOException {
        if(myStore.checkAccountAndLogin(idText.getText(), passwordText.getText()))
            changeSceneToMainPage();
        else
            signInErrorLabel.setText("Invalid username or password!");
    }

    public abstract void changeSceneToMainPage() throws IOException;

}
