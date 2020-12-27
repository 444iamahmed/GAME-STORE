package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class TitleInGridController {

    protected Object mainPageController;
    @FXML
    Label name, price;
    @FXML
    Button addToCartButton;

    protected Title title;
    Store myStore;


    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public Title getMyTitle() {
        return title;
    }

    public void setMyTitle(Title myTitle) {
        this.title = myTitle;
        setPriceLabel(myTitle.getPrice());
        setNameLabel(myTitle.getName());
    }

    void setPriceLabel(Double priceValue)
    {
        price.setText(price.getText() + priceValue.toString());
    }
    void setNameLabel(String nameValue)
    {
        name.setText(nameValue);
    }

    public void openTitlePage() throws IOException {
        ((MainPageCustomerController) mainPageController).openTitlePage(title);
    }


    public void setController(Object controller) {
        mainPageController = controller;
    }

    public void addToCart()
    {
        myStore.addToCart(title);
    }

}
