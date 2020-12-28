package sample;

import javafx.scene.control.Alert;

import java.io.IOException;

public class TitleInGridController extends AccessibleTitleController {





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

    @Override
    public void openTitlePage() throws IOException {
        ((MainPageCustomerController) mainPageController).openTitlePage(title);
    }
}
