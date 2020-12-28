package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public abstract class AccessibleTitleController {
    protected Object mainPageController;
    @FXML
    Label nameLabel, priceLabel;


    protected Title title;
    Store myStore;


    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public Title getMyTitle() {
        return title;
    }

    public void fillData(Title myTitle) {
        this.title = myTitle;
        setPriceLabel(myTitle.getPrice());
        setNameLabel(myTitle.getName());
    }

    public void setController(Object controller) {
        mainPageController = controller;
    }

    void setPriceLabel(Double priceValue)
    {
        priceLabel.setText(priceLabel.getText() + priceValue.toString());
    }
    void setNameLabel(String nameValue)
    {
        nameLabel.setText(nameValue);
    }

    public abstract void openTitlePage() throws IOException;

}
