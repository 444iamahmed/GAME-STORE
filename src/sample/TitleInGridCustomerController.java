package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;

public class TitleInGridCustomerController extends AccessibleTitleController {


    @FXML
    Label priceLabel;



    public void addToCart()
    {
        if(!myStore.addToCart(title))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Out of Stock!");
            alert.showAndWait();
        }
    }



    void setPriceLabel(Double priceValue)
    {
        priceLabel.setText(priceLabel.getText() + priceValue.toString());
    }

    @Override
    public void fillData(Title myTitle) {
        super.fillData(myTitle);
        setPriceLabel(myTitle.getPrice());
    }
}