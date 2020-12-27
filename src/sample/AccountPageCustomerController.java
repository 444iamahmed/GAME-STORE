package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AccountPageCustomerController extends AccountPageController{

    @FXML
    TreeView ownedTitlesContainer;

    @Override
    public void initialize() {
        super.initialize();
        fillOwnedTitlesContainer();
    }

    private void fillOwnedTitlesContainer() {
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
        ownedTitlesContainer = new TreeView(root);
        ownedTitlesContainer.setShowRoot(false);
    }


    @Override
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

            myStore.deleteCustomerAccount(myAccount);
            if(myAccount.getEmail() == myStore.getActiveAccount().getEmail())
                changeSceneToSignIn();
        }

    }

    @Override
    public void changeSceneToSignIn() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignInPageCustomer.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) deleteAccountButton.getScene().getWindow();
        window.setScene(loginScene);
    }
}
