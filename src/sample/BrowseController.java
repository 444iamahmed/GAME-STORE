package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class BrowseController {

    @FXML
    TableView<Title> titles;
    @FXML
    TextField searchText;
    @FXML
    Button backLogin, account, search;

    @FXML
    VBox genreVBox, platformVBox;

    @FXML
    Slider rating, price;

    ArrayList<String> genres, platforms;
    Filter filters;
    Store myStore;
    public void initialize()
    {
        genres = Genres.getInstance().genres;
        platforms = GamePlatforms.getInstance().platforms;
        myStore = Store.getInstance();
        filters = new Filter();
       for(String m: genres)
       {
           CheckBox tempCheckBox = new CheckBox(m);
           tempCheckBox.selectedProperty().addListener((v, oldValue, newValue) -> {
               SetGenre(tempCheckBox, newValue);
               Search();
           });
           genreVBox.getChildren().add(tempCheckBox);
       }
        for(String m: platforms)
        {
            CheckBox tempCheckBox = new CheckBox(m);
            tempCheckBox.selectedProperty().addListener((v, oldValue, newValue) -> {
                SetPlatform(tempCheckBox, newValue);
                Search();
            });
            platformVBox.getChildren().add(tempCheckBox);
        }



        TableColumn<Title, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Title, String> developerColumn = new TableColumn<>("Developer");
        developerColumn.setMinWidth(100);
        developerColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));

        TableColumn<Title, Date> releaseDateColumn = new TableColumn<>("Release Date");
        releaseDateColumn.setMinWidth(100);
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));


        TableColumn<Title, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setMinWidth(100);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

       
        TableColumn<Title, Float> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setMinWidth(100);
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Title, Float> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        titles.setItems(myStore.Search(filters));
        titles.getColumns().addAll(nameColumn, developerColumn, releaseDateColumn, genreColumn, ratingColumn, priceColumn);

        rating.valueChangingProperty().addListener((v, oldValue, newValue) -> {
            SetRating(rating);
            Search();
        });

        price.valueChangingProperty().addListener((v, oldValue, newValue) -> {
            SetPrice(price);
            Search();
        });
    }

    public void changeSceneToLogin() throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) backLogin.getScene().getWindow();
        window.setScene(loginScene);
    }
    public void changeSceneToAccount() throws IOException
    {
        Parent accountParent = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene accountScene = new Scene(accountParent);

        Stage window = (Stage) account.getScene().getWindow();
        window.setScene(accountScene);
    }


    public void Search()
    {
        System.out.println(searchText.getText() + " searched");
        filters.setSearchText(searchText.getText());
        titles.getItems().clear();
        titles.setItems(myStore.Search(filters));
    }

    void SetGenre(CheckBox box, boolean val)
    {
       if(box.isSelected())
           filters.addGenre(box.getText());
       else
           filters.removeGenre(box.getText());
    }
    void SetPlatform(CheckBox box, boolean val)
    {
        if(box.isSelected())
            filters.addGenre(box.getText());
        else
            filters.removeGenre(box.getText());
    }

    void SetRating(Slider slider)
    {
        filters.setRating((float) slider.getValue());
    }
    void SetPrice(Slider slider)
    {
        filters.setMaxPrice((float) slider.getValue());
    }



}
