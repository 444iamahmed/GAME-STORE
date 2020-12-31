package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OrderContainerController {

    @FXML
    Label orderNoText, totalText;
    @FXML
    VBox orderContainer;

    public void fillContainer(Order order) throws IOException {

        orderNoText.setText(orderNoText.getText() + order.getOrderNumber());
        totalText.setText(totalText.getText() + order.getTotal().toString());
        for(Title i: order.getTitles())
        {
            FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("TitleInListNotClickable.fxml"));
            orderContainer.getChildren().add(titleLoader.load());
            TitleInListController titleInList = titleLoader.getController();

            titleInList.fillData(i);
        }
    }

}
