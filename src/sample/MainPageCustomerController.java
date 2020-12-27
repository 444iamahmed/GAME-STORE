package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageCustomerController extends MainPageController{


    public void changeTabToBrowse() throws IOException {
        BrowsePageController browsePageController = (BrowsePageController) loadPage("BrowsePage");
        browsePageController.setMyController(this);
        browsePageController.fillTitlesContainer();
        pageLabel.setText("Browse");
    }
    public void changeTabToAccount()
    {
        AccountPageCustomerController accountPageCustomerController = (AccountPageCustomerController) loadPage("AccountPageCustomer");
        accountPageCustomerController.fillAccountData(myStore.getActiveAccount());
        pageLabel.setText("Account");
    }
    public void changeTabToCart() throws IOException {
        CartPageController cartPageController = (CartPageController) loadPage("CartPage");
        pageLabel.setText("Cart");
        cartPageController.setMyController(this);
        cartPageController.refreshList();
    }


    public void changeSceneToLogin() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignInPageCustomer.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) signOutButton.getScene().getWindow();
        window.setScene(loginScene);
    }

    @Override
    public void openTitlePage(Title myTitle) throws IOException {
        TitlePageCustomerController titlePageCustomerController = (TitlePageCustomerController) loadPage("TitlePageCustomer");
        titlePageCustomerController.fillTitleData(myTitle);
    }
}
