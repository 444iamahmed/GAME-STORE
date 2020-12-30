package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageAdminController extends MainPageController{


    @Override
    public void changeTabToHome()
    {
        loadPage("HomePageAdmin");
        pageLabel.setText("Home");
    }
    public void changeTabToTitles() throws IOException {
        BrowsePageAdminController browsePageController = (BrowsePageAdminController) loadPage("BrowsePageAdmin");
        browsePageController.setMainPageController(this);
        browsePageController.fillTitlesContainer();
        pageLabel.setText("Titles");
    }
    public void changeTabToAdmins() throws IOException {
        AdminsPageController adminsPageController = (AdminsPageController) loadPage("AdminsPage");
        adminsPageController.setMainPageController(this);
        adminsPageController.fillAdminsContainer();
        pageLabel.setText("Admins");
    }
    public void changeTabToCustomers() throws IOException {
        CustomersPageController customersPageController = (CustomersPageController) loadPage("CustomersPage");
        customersPageController.setMainPageController(this);
        pageLabel.setText("Customers");
    }


    public void changeSceneToSignIn() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignInPageAdmin.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) signOutButton.getScene().getWindow();
        window.setScene(loginScene);
    }

    @Override
    public void openTitlePage(Title myTitle) throws IOException {
        TitlePageAdminController titlePageAdminController = (TitlePageAdminController) loadPage("TitlePageAdmin");
        titlePageAdminController.fillTitleData(myTitle);
    }

    public void openAdminPage(Account account) throws IOException{
        AccountPageAdminController accountPageAdminController = (AccountPageAdminController) loadPage("AccountPageAdmin");
        accountPageAdminController.fillAccountData(account);
    }
}
