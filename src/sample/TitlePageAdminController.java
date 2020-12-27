package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class TitlePageAdminController extends TitlePageCustomerController{

    @FXML
    TextField priceText, developerText, releaseDateText;

    @FXML
    Slider ratingSlider;

    @FXML
    VBox platformOptions;
    VBox genreOptions;

    ToggleGroup platformToggleGroup;
    ArrayList<String> genres;
    ArrayList<String> platforms;

    public void initialize()
    {
        super.initialize();
        genres = myStore.getGenres();
        platforms = myStore.getPlatforms();
        for(String m: genres)
        {
            CheckBox tempCheckBox = new CheckBox(m);
            genreOptions.getChildren().add(tempCheckBox);
        }
        for(String m: platforms)
        {
            RadioButton tempRadioButton = new RadioButton(m);
            platformOptions.getChildren().add(tempRadioButton);
            platformToggleGroup.getToggles().add(tempRadioButton);
        }




    }

    @Override
    public void setTitlePage(Title myTitle) {
        title = myTitle;
        priceText.setText(myTitle.getPrice().toString());
        for(Node m: genreOptions.getChildren())
        {
            if(myTitle.getGenre().contains(((CheckBox)m).getText()))
            {
                ((CheckBox) m).setScaleShape(true);
            }
        }

        for(Toggle m: platformToggleGroup.getToggles())
        {
            if(((RadioButton) m).getText().equals(myTitle.getPlatform()))
            {
                platformToggleGroup.selectToggle(m);
                break;
            }
        }
        descriptionTextArea.setText(myTitle.getDescription());
        developerText.setText(myTitle.getDeveloper());
        releaseDateText.setText(myTitle.getReleaseDate().toString());
    }

    public void saveChanges()
    {

    }
}
