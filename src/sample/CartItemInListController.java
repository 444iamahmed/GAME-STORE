package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class CartItemInListController {

    @FXML
    Label priceLabel;
    @FXML
    ImageView titleImageView;

    CartPageController myController;
    Store myStore;
    CartItem me;

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void remove() throws IOException {
        myStore.removeFromCart(me);
        myController.refreshList();
    }

    public void setController(CartPageController controller)
    {
        this.myController = controller;
    }

    void setItem(CartItem item)
    {
        me = item;
        priceLabel.setText(priceLabel.getText() + me.getPrice().toString());

    }

}
