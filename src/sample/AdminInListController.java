package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Optional;

public class AdminInListController {

    private MainPageAdminController mainPageController;
    @FXML
    Label usernameLabel,  emailLabel;

    Store myStore;
    Account myAccount;

    public void initialize()
    {
        myStore = Store.getInstance();
    }
    public void openAdminPage() throws IOException {
        mainPageController.openAdminPage(myAccount);
    }

    public void setMainPageController(MainPageAdminController controller)
    {
        mainPageController = controller;
    }


    public void setMyAccount(Account account) {
        myAccount = account;
        usernameLabel.setText(account.getUsername());
        emailLabel.setText(account.getEmail());
    }

    public void deleteAccount() throws IOException {

        String ownAccountWarning = "";
        boolean flag = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        if(myAccount.getEmail() == myStore.getActiveAccount().getEmail())
        {
            flag = true;
            ownAccountWarning = "\nThis is your own account so you will be signed out!";
        }

        alert.setTitle("Confirmation");

        alert.setContentText("Are you sure you want to delete this account?" + ownAccountWarning);

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == buttonTypeYes)
        {

            myStore.deleteAdminAccount(myAccount);
            if(flag)
                mainPageController.changeSceneToSignIn();
            else
                mainPageController.changeTabToAdmins();
        }

    }

}
