package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainPageAdminController {

    @FXML
    BorderPane mainPageBorderPane;

    @FXML
    Button signOutButton;

    @FXML
    Label pageLabel;

    public void changeTabToHome()
    {
        loadPage("HomePageCustomer");
        pageLabel.setText("Home");
    }
    public void changeTabToTitles() throws IOException {
//        BrowsePageController browsePageController = (BrowsePageController) loadPage("BrowsePage");
//        browsePageController.setMyController(this);
//        browsePageController.fillGrid();
//        pageLabel.setText("Browse");
    }
    public void changeTabToAdmins()
    {
        loadPage("AccountPage");
        pageLabel.setText("Account");
    }
    public void changeTabToCustomers() throws IOException {
//        CartPageController cartPageController = (CartPageController) loadPage("CartPage");
//        pageLabel.setText("Cart");
//        cartPageController.setMyController(this);
//        cartPageController.refreshList();
    }
    private Object loadPage(String page)
    {
        Parent root = null;
        FXMLLoader pageLoader = null;
        try {
            pageLoader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            root = pageLoader.load();
        } catch (IOException e) {
            Logger.getLogger(MainPageAdminController.class.getName()).log(Level.SEVERE, null, e);
        }
        mainPageBorderPane.setCenter(root);
        return pageLoader.getController();
    }

    public void changeSceneToLogin() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignInPage.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) signOutButton.getScene().getWindow();
        window.setScene(loginScene);
    }

    public void openTitlePage(Title myTitle) throws IOException {
        TitlePageCustomerController titlePageCustomerController = (TitlePageCustomerController) loadPage("TitlePageCustomer");
        titlePageCustomerController.setTitlePage(myTitle);
    }
}
