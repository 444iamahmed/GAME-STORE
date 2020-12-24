package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AccountPageController {

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

    VBox boxybox;

    Store myStore;
    Account activeAccount;
    boolean[] conditions = {false, false, false};

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
                if(Validator.validatePasswordIntegrity(newValue))
                {
                    accountPagePasswordError.setText("");
                    accountPageRepeatPassword.setDisable(false);
                    conditions[1] = true;
                }
                else
                    accountPagePasswordError.setText(Validator.passwordErrorMessage);
            }
            else
                accountPageRepeatPassword.setDisable(true);
            resetSaveChangesButton();
        });

        accountPageRepeatPassword.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[2] = false;
            if(newValue == accountPagePassword.getText())
            {
                accountPageRepeatPasswordError.setText("");
                conditions[2] = true;
            }
            else
                accountPageRepeatPasswordError.setText("Passwords don't match!");
            resetSaveChangesButton();
        });
        //activeAccount = myStore.getActiveAccount();
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
        Parent browseParent = FXMLLoader.load(getClass().getResource("BrowsePage.fxml"));
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

    public void changeSceneToLogin() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignInPage.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) deleteAccountButton.getScene().getWindow();
        window.setScene(loginScene);
    }
}