package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountController {

    @FXML
    Button backToBrowse, deleteAccountButton, saveChangesButton;
    @FXML
    TreeView ownedTitles;
    @FXML
    TextField accountPageUsername, accountPageEmail;
    @FXML
    PasswordField accountPagePassword, accountPageRepeatPassword;
    @FXML
    Label accountPageUsernameError, accountPagePasswordError, accountPageRepeatPasswordError;

    Store myStore;
    Account activeAccount;
    boolean[] conditions = {false, false, false};
    String passwordErrorMessage = "Password must contain at least 1 of (0-9, a-z, A-Z) and must have a length between 8 and 40 characters!";

    public void initialize()
    {

        myStore = Store.getInstance();
        activeAccount = myStore.getActiveAccount();


        accountPageUsername.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[0] = false;
            if(newValue != activeAccount.getUsername())
            {
                if(!myStore.usernameExists(newValue))
                {
                    accountPageUsernameError.setText("");
                    conditions[0] = true;
                }
                else
                    accountPageUsernameError.setText("Username already exists!");
            }
            resetSaveChangesButton();

        });
        accountPagePassword.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[1] = false;
            if(newValue != activeAccount.getPassword())
            {
                if(validatePasswordIntegrity())
                {
                    accountPagePassword.setText("");
                    accountPageRepeatPassword.setDisable(false);
                    conditions[1] = true;
                }
                else
                    accountPageUsernameError.setText(passwordErrorMessage);
            }
            else
                accountPagePassword.setDisable(true);
            resetSaveChangesButton();
        });

        accountPageRepeatPassword.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[2] = false;
            if(newValue == accountPagePassword.getText())
            {
                accountPageRepeatPassword.setText("");
                conditions[2] = true;
            }
            else
                accountPageUsernameError.setText("Passwords don't match!");
            resetSaveChangesButton();
        });
        accountPageEmail.setText(activeAccount.getEmail());
        accountPageUsername.setText(activeAccount.getUsername());
        accountPagePassword.setText(activeAccount.getPassword());

        TreeItem<Displayable> root;
        root = new TreeItem<>();
        root.setExpanded(true);

        for(Title t: myStore.getOwnedKeys())
        {
            TreeItem<Displayable> titleTreeItem = new TreeItem<>(t);
            root.getChildren().add(titleTreeItem);
            for(Key key: t.getKeys())
            {
                titleTreeItem.getChildren().add(new TreeItem<>(key));
            }

        }
        ownedTitles = new TreeView(root);
        ownedTitles.setShowRoot(false);


    }
    void resetSaveChangesButton()
    {
        boolean flag = true;
        for(boolean i: conditions)
            if(!i)
                flag = false;
        saveChangesButton.setDisable(!flag);
    }
    public void changeSceneToBrowse() throws IOException
    {
        Parent browseParent = FXMLLoader.load(getClass().getResource("Browse.fxml"));
        Scene browseScene = new Scene(browseParent);

        Stage window = (Stage) backToBrowse.getScene().getWindow();
        window.setScene(browseScene);
    }

    public void saveChanges()
    {
        activeAccount.setPassword(accountPagePassword.getText());
        activeAccount.setUsername(accountPageUsername.getText());
        myStore.saveActiveAccountChanges(activeAccount);
    }
    public void deleteAccount() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to delete your account?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK)
        {
            myStore.deleteActiveAccount();
            changeSceneToLogin();
        }

    }

    boolean validatePasswordIntegrity()
    {
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,40}$");
        Matcher matcher = p.matcher(accountPagePassword.getText());
        return matcher.matches();
    }

    public void changeSceneToLogin() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) deleteAccountButton.getScene().getWindow();
        window.setScene(loginScene);
    }
}
