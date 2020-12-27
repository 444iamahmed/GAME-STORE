package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminsPageController extends UsersPageController {

    @FXML
    VBox adminsContainer;

    @Override
    public void initialize() throws IOException {
        super.initialize();
        fillAdminsContainer();
    }

    @Override
    public void search() {
        myStore.searchAdmins(filter);
    }

    public void fillAdminsContainer() throws IOException {
        adminsContainer.getChildren().clear();

        for(Account i: myStore.searchAdmins(filter))
        {
            FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("AdminInList.fxml"));
            Parent adminInList = adminLoader.load();
            AdminInListController adminInListController = adminLoader.getController();
            adminInListController.setMyAccount(i);
            adminInListController.setMainPageController((MainPageAdminController) controller);
            adminsContainer.getChildren().add(adminInList);

        }
    }
}
