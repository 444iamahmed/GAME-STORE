package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class TitlePageCustomerController {

    @FXML
    Label priceLabel, ratingLabel, platformLabel, genresLabel;

    @FXML
    TextFlow descriptionTextFlow;


    Title title;
    Store myStore;

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void setTitlePage(Title myTitle) {
        title = myTitle;
        priceLabel.setText(priceLabel.getText() + myTitle.getPrice().toString());
        ratingLabel.setText(ratingLabel.getText() + myTitle.getRating().toString());
        platformLabel.setText(platformLabel.getText() + myTitle.getPlatform());
        genresLabel.setText(genresLabel.getText() + myTitle.getGenreString());
        descriptionTextFlow.getChildren().add(new Text(myTitle.getDescription()));
    }
    public void addToCart()
    {
        myStore.addToCart(title);
    }
}
