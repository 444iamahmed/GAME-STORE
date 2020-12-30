package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public abstract class AccessibleTitleController {


    protected MainPageController mainPageController;

    @FXML
    Label nameLabel;
    @FXML
    Label priceLabel;


    protected Title title;
    Store myStore;


    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public Title getTitle() {
        return title;
    }

    public void openTitlePage() throws IOException {
        mainPageController.openTitlePage(title);
    }
    public void fillData(Title myTitle) {
        this.title = myTitle;
        nameLabel.setText(myTitle.getName());
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }
}
