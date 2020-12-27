package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MainPageController {


    @FXML
    BorderPane mainPageBorderPane;

    @FXML
    Button signOutButton;

    @FXML
    Label pageLabel;

    Store myStore;

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void changeTabToHome()
    {
        loadPage("HomePage");
        pageLabel.setText("Home");
    }
    protected Object loadPage(String page)
    {
        Parent root = null;
        FXMLLoader pageLoader = null;
        try {
            pageLoader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            root = pageLoader.load();
        } catch (IOException e) {
            Logger.getLogger(MainPageCustomerController.class.getName()).log(Level.SEVERE, null, e);
        }
        mainPageBorderPane.setCenter(root);
        return pageLoader.getController();
    }
    public abstract void openTitlePage(Title myTitle) throws IOException;
}
