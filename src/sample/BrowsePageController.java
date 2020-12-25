package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;


public class BrowsePageController {

    MainPageController myController;

    @FXML
    GridPane titlesContainer;
    @FXML
    TextField searchTextBrowse;


    @FXML
    VBox genreVBox, platformVBox;

    @FXML
    Slider rating, price;

    ArrayList<String> genres, platforms;
    Filter filter;
    Store myStore;
    public void initialize() throws IOException {

        myStore = Store.getInstance();
        genres = myStore.genres;
        platforms = myStore.platforms;
        filter = Filter.getInstance();
        for(String m: genres)
       {
           CheckBox tempCheckBox = new CheckBox(m);
           tempCheckBox.selectedProperty().addListener((v, oldValue, newValue) -> {
               setGenre(tempCheckBox);
               try {
                   Search();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
           genreVBox.getChildren().add(tempCheckBox);
       }
        for(String m: platforms)
        {
            CheckBox tempCheckBox = new CheckBox(m);
            tempCheckBox.selectedProperty().addListener((v, oldValue, newValue) -> {
                setPlatform(tempCheckBox);
                try {
                    Search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            platformVBox.getChildren().add(tempCheckBox);
        }

        rating.valueChangingProperty().addListener((v, oldValue, newValue) -> {
            setRating(rating);
            try {
                Search();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        price.valueChangingProperty().addListener((v, oldValue, newValue) -> {
            setPrice(price);
            try {
                Search();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void fillGrid() throws IOException
    {
        titlesContainer.getChildren().clear();
        int maxCol = 3, rowCnt = 0, colCnt = 0;
        for(Title i: myStore.search(filter))
        {
            FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("TitleInGrid.fxml"));
            Parent titleInGrid = titleLoader.load();
            TitleInGridController titleController = titleLoader.getController();
            titleController.setMyTitle(i);
            titleController.setController(myController);
            titlesContainer.add(titleInGrid, colCnt, rowCnt);
            colCnt++;

            if(colCnt > maxCol)
            {
                rowCnt++;
                colCnt = 0;
            }
        }
    }

    public void Search() throws IOException
    {
        filter.setSearchText(searchTextBrowse.getText());
        fillGrid();
    }

    void setGenre(CheckBox box)
    {
       if(box.isSelected())
           filter.addGenre(box.getText());
       else
           filter.removeGenre(box.getText());
    }
    void setPlatform(CheckBox box)
    {
        if(box.isSelected())
            filter.addPlatform(box.getText());
        else
            filter.removePlatform(box.getText());
    }

    void setRating(Slider slider)
    {
        filter.setRating(slider.getValue());
    }
    void setPrice(Slider slider)
    {
        filter.setMaxPrice(slider.getValue());
    }


    public void setMyController(MainPageController mainPageController) {
        this.myController = mainPageController;
    }
}
