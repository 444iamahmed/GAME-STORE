package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;


public class BrowsePageController {

    MainPageCustomerController myController;

    @FXML
    ToggleGroup sortToggleGroup, releaseToggleGroup, sortOrderToggleGroup;

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
        for(Toggle i: sortToggleGroup.getToggles())
        {
            if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("price"))
            {
                i.setUserData(SortBy.PRICE);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("date"))
            {
                i.setUserData(SortBy.DATE);
            }
            else
            {
                i.setUserData(SortBy.RATING);
            }
        }

        for(Toggle i: releaseToggleGroup.getToggles())
        {
            if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("all time"))
            {
                i.setUserData(Released.ALL_TIME);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("this year"))
            {
                i.setUserData(Released.THIS_YEAR);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("this month"))
            {
                i.setUserData(Released.THIS_MONTH);
            }
            else
            {
                i.setUserData(Released.THIS_WEEK);
            }
        }

        for(Toggle i: sortOrderToggleGroup.getToggles())
        {
            ((RadioButton) i).setUserData(((RadioButton) i).getText());
        }

        sortToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                filter.setSortBy((SortBy) t1.getUserData());
                try {
                    Search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        releaseToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                filter.setReleased((Released) t1.getUserData());
                try {
                    Search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sortOrderToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                filter.setOrderBy((String) t1.getUserData());
                try {
                    Search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public void setMyController(MainPageCustomerController mainPageCustomerController) {
        this.myController = mainPageCustomerController;
    }
}
