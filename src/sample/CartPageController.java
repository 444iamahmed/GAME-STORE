package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CartPageController {

    MainPageController myController;
    @FXML
    VBox cartItemsList;
    @FXML
    Label totalPriceLabel;

    @FXML
    TextField cardNumberText, expirationText, CVVText;

    Store myStore;

    public void initialize() throws IOException {
        myStore = Store.getInstance();
    }

    public void refreshList() throws IOException {
        cartItemsList.getChildren().clear();

        for(CartItem i: myStore.getCartItems())
        {
            FXMLLoader cartItemLoader = new FXMLLoader(getClass().getResource("CartItemInList.fxml"));
            cartItemsList.getChildren().add(cartItemLoader.load());
            CartItemInListController cartItemInList = cartItemLoader.getController();
            cartItemInList.setController(this);
            cartItemInList.setItem(i);
        }
        totalPriceLabel.setText(totalPriceLabel.getText() + myStore.getCartTotal().toString());

    }


    public void checkout()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to checkout?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == buttonTypeYes)
        {
            myStore.checkout(cardNumberText.getText(), expirationText.getText(), CVVText.getText());
            Alert orderDetails = new Alert(Alert.AlertType.INFORMATION);
            orderDetails.setTitle("Order Details");
            orderDetails.setHeaderText("Order No. " + myStore.generateOrderNumber().toString() +"\n with Total: Rs. " + myStore.getCartTotal().toString());
            myStore.clearCart();
            orderDetails.showAndWait();
            myController.changeTabToHome();
        }

    }

    void setMyController(MainPageController controller)
    {
        myController = controller;
    }



}
