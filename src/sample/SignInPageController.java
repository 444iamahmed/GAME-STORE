package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class SignInPageController {

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
            changeSceneToMainPage();
        else
            loginError.setText("Invalid username or password!");
    }

    public void changeSceneToMainPage() throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPageCustomer.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);

        Stage window = (Stage) signInButton.getScene().getWindow();
        window.setScene(mainPageScene);
    }

    public void changeSceneToSignUp() throws  IOException
    {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        Scene signUpScene = new Scene(signUpParent);

        Stage window = (Stage) signInPageToSignUpButton.getScene().getWindow();
        window.setScene(signUpScene);
    }

}
