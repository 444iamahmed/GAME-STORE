package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpController {

    @FXML
    TextField username, email, repeatEmail, password, repeatPassword;

    @FXML
    Label usernameError, emailError, repeatEmailError, passwordError, repeatPasswordError;

    @FXML
    Button back, signUp;

    Store myStore;
    boolean[] condition = {false, false, false, false, false};
    String passwordErrorMessage = "Password must contain at least 1 of (0-9, a-z, A-Z) and must have a length between 8 and 40 characters!";
    public void initialize()
    {
        signUp.setDisable(true);

        myStore = Store.getInstance();
        username.textProperty().addListener((v, oldValue, newValue) -> {

            condition[0] = false;
            if(myStore.usernameExists(newValue))
                usernameError.setText("Username already exists!");
            else
            {
                usernameError.setText("");
                condition[0] = true;
            }
            resetSignUpButton();
        });

        email.textProperty().addListener((v, oldValue, newValue) -> {

            condition[1] = false;
            if(validateEmailFormat())
                emailError.setText("Email Invalid!");
            else if(myStore.emailExists(newValue))
                emailError.setText("Email already exists!");
            else
            {
                emailError.setText("");
                condition[1] = true;
            }
            resetSignUpButton();
        });

        repeatEmail.textProperty().addListener((v, oldValue, newValue) -> {

            condition[2] = false;
            if(!repeatEmailMatches())
                repeatEmailError.setText("Emails don't match!");
            else
            {
                repeatEmailError.setText("");
                condition[2] = true;
            }
            resetSignUpButton();
        });

        password.textProperty().addListener((v, oldValue, newValue) -> {

            condition[3] = false;
            if(!validatePasswordIntegrity())
                passwordError.setText(passwordErrorMessage);
            else
            {
                passwordError.setText("");
                condition[3] = true;
            }
            resetSignUpButton();
        });

        repeatPassword.textProperty().addListener((v, oldValue, newValue) -> {

            condition[4] = false;
            if(!repeatPasswordMatches())
                repeatPassword.setText("Passwords don't match!");
            else
            {
                repeatPassword.setText("");
                condition[4] = true;
            }
            resetSignUpButton();
        });

    }
    void resetSignUpButton()
    {
        boolean flag = true;
        for(boolean i: condition)
            if(!i)
                flag = false;

        signUp.setDisable(!flag);
    }

    boolean validateEmailFormat()
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if(m.find() && m.group().equals(email.getText()))
            return true;
        return false;
    }

    boolean repeatEmailMatches()
    {
        if(email.getText() == repeatEmail.getText())
            return true;
        return false;
    }
    boolean repeatPasswordMatches()
    {
        if(password.getText() == repeatPassword.getText())
            return true;
        return false;
    }
    boolean validatePasswordIntegrity()
    {
        Pattern p = Pattern.compile("((?=.*[a-z])(?=.*\\\\d)(?=.*[A-Z]).{8,40})");
        Matcher m = p.matcher(password.getText());
        if(m.find() && m.group().equals(password.getText()))
            return true;
        return false;
    }

    public void makeAccount()
    {
        myStore.saveAccountAndSetActive(username.getText(), email.getText(), password.getText());
    }

    public void changeSceneToBrowse() throws IOException
    {
        Parent browseParent = FXMLLoader.load(getClass().getResource("Browse.fxml"));
        Scene browseScene = new Scene(browseParent);

        Stage window = (Stage) signUp.getScene().getWindow();
        window.setScene(browseScene);
    }

    public void changeSceneToLogin() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(loginScene);
    }
}
