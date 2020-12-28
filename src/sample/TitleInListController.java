package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.util.Set;

public class TitleInListController extends AccessibleTitleController{

    @FXML
    ChoiceBox keysContainer;
    @Override
    public void fillData(Title myTitle) {
        super.fillData(myTitle);
        keysContainer.setItems(FXCollections.observableArrayList(title.getKeys().toArray()));
    }

    @Override
    public void openTitlePage() throws IOException {
        ((MainPageCustomerController) mainPageController).openTitlePage(title);
    }
}
