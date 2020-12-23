package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    @FXML Button signInButton, signInPageToSignUpButton;

    @FXML
    TextField loginId;
    @FXML
    PasswordField loginPassword;

    @FXML
    Label loginError;

    Store myStore;
    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void validateCredentials() throws IOException {
        if(myStore.checkAccountAndLogin(loginId.getText(), loginPassword.getText()))
            changeSceneToBrowse();
        else
            loginError.setText("Invalid username or password!");
    }

    public void changeSceneToBrowse() throws IOException
    {
        Parent browseParent = FXMLLoader.load(getClass().getResource("Browse.fxml"));
        Scene browseScene = new Scene(browseParent);

        Stage window = (Stage) signInButton.getScene().getWindow();
        window.setScene(browseScene);
    }

    public void changeSceneToSignUp() throws  IOException
    {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene signUpScene = new Scene(signUpParent);

        Stage window = (Stage) signInPageToSignUpButton.getScene().getWindow();
        window.setScene(signUpScene);
    }

}
