package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AdminsPageController extends UsersPageController {

    @FXML
    VBox adminsContainer;

    @Override
    public void initialize() {
        super.initialize();
        fillAdminsContainer();
    }

    @Override
    public void search() {
        myStore.searchAdmins(filter);
    }

    public void fillAdminsContainer()
    {

    }
}
