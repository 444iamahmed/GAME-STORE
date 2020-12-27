package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public abstract class AccountPageController {

    @FXML
    Button deleteAccountButton,
            saveChangesButton;

    @FXML
    TextField usernameText, emailText;
    @FXML
    PasswordField passwordText, confirmPasswordText;
    @FXML
    Label usernameErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel;


    Store myStore;
    Account myAccount;
    boolean[] conditions = {false, false, false};

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void fillAccountData(Account account) {
        myAccount = account;

        usernameText.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[0] = false;
            if(newValue != myAccount.getUsername())
            {
                if(!myStore.usernameExists(newValue))
                {
                    usernameErrorLabel.setText("");
                    conditions[0] = true;
                }
                else
                    usernameErrorLabel.setText("Username already exists!");
            }
            resetSaveChangesButton();

        });
        passwordText.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[1] = false;
            if(newValue != myAccount.getPassword())
            {
                if(Validator.validatePasswordIntegrity(newValue))
                {
                    passwordErrorLabel.setText("");
                    confirmPasswordText.setDisable(false);
                    conditions[1] = true;
                }
                else
                    passwordErrorLabel.setText(Validator.passwordErrorMessage);
            }
            else
                confirmPasswordText.setDisable(true);
            resetSaveChangesButton();
        });

        confirmPasswordText.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[2] = false;
            if(newValue == passwordText.getText())
            {
                confirmPasswordErrorLabel.setText("");
                conditions[2] = true;
            }
            else
                confirmPasswordErrorLabel.setText("Passwords don't match!");
            resetSaveChangesButton();
        });

        emailText.setText(myAccount.getEmail());
        usernameText.setText(myAccount.getUsername());
        passwordText.setText(myAccount.getPassword());
    }

    void resetSaveChangesButton()
    {
        boolean flag = true;
        for(boolean i: conditions)
            if(!i)
                flag = false;
        saveChangesButton.setDisable(!flag);
    }

    public void saveChanges()
    {
        myAccount.setPassword(passwordText.getText());
        myAccount.setUsername(usernameText.getText());
        myStore.saveAccountChanges(myAccount);
    }
    public void deleteAccount() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to delete your account?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == buttonTypeYes)
        {
            myStore.deleteActiveAccount();
            changeSceneToLogin();
        }

    }

    public abstract void  changeSceneToLogin() throws IOException;
}
