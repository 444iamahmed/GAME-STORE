package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.util.Set;

public class TitleInListController extends AccessibleTitleController{

    @FXML
    ChoiceBox keysContainer;
    @Override
    public void fillData(Title myTitle) {
        super.fillData(myTitle);
        keysContainer.setItems(FXCollections.observableArrayList(title.getKeysStrings()));
    }

    @Override
    public void openTitlePage() throws IOException {
        if(!title.doesExist())
            showExistenceError();
        else
            mainPageController.openTitlePage(title);
    }
    void showExistenceError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Not In System!");
        alert.showAndWait();
    }
}
