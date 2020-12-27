package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void changeSceneToLogin() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignInPageCustomer.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) deleteAccountButton.getScene().getWindow();
        window.setScene(loginScene);
    }
}
