package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class SignInPageCustomerController extends SignInPageController {

    @FXML Button signUpButton;



    @Override
    public void changeSceneToMainPage() throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPageCustomer.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);

        Stage window = (Stage) signInButton.getScene().getWindow();
        window.setScene(mainPageScene);
    }

    public void changeSceneToSignUp() throws  IOException
    {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("SignUpPageCustomer.fxml"));
        Scene signUpScene = new Scene(signUpParent);

        Stage window = (Stage) signUpButton.getScene().getWindow();
        window.setScene(signUpScene);
    }

}
