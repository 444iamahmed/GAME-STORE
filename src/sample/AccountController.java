package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class AccountController {

    @FXML
    Button login;
    @FXML
    TreeView ownedTitles;

    Store myStore;

    public void initialize()
    {

        myStore = Store.getInstance();
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

    public void changeSceneToBrowse() throws IOException
    {
        Parent browseParent = FXMLLoader.load(getClass().getResource("Browse.fxml"));
        Scene browseScene = new Scene(browseParent);

        Stage window = (Stage) login.getScene().getWindow();
        window.setScene(browseScene);
    }



}
