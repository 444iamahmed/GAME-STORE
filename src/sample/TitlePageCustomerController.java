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
    Label priceLabel, ratingLabel, platformLabel, genresLabel, developerLabel, releaseDateLabel;

    @FXML
    TextArea descriptionTextArea;


    Title title;
    Store myStore;

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void fillTitleData(Title myTitle) {
        title = myTitle;
        priceLabel.setText(priceLabel.getText() + myTitle.getPrice().toString());
        ratingLabel.setText(ratingLabel.getText() + myTitle.getRating().toString());
        platformLabel.setText(platformLabel.getText() + myTitle.getPlatform());
        genresLabel.setText(genresLabel.getText() + myTitle.getGenreString());
        descriptionTextArea.setText(myTitle.getDescription());
        developerLabel.setText(developerLabel.getText() + myTitle.getDeveloper());
        releaseDateLabel.setText(releaseDateLabel.getText() + myTitle.getReleaseDate().toString());
    }
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

    public Title getTitle() {
        return title;
    }
}
