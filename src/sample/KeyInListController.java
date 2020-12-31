package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class KeyInListController {
    TitlePageAdminController titlePageAdminController;

    @FXML
    Label keyLabel;

    Key key;

    public void remove() throws IOException {
        titlePageAdminController.getTitle().removeKey(key);
        titlePageAdminController.fillKeysContainer();
    }

    void setTitlePageAdminController(TitlePageAdminController controller)
    {
        titlePageAdminController = controller;
    }


    public void setKey(Key i) {
        key = i;
        keyLabel.setText(key.getValue());
    }
}
