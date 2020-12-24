package sample;

import java.io.IOException;
import java.util.ArrayList;

public class CartItemInListController {

    CartPageController myController;
    Store myStore;
    CartItem me;

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void removeFromCart() throws IOException {
        myStore.removeFromCart(me);
        myController.refreshList();
    }

    public void setController(CartPageController controller)
    {
        this.myController = controller;
    }

}
