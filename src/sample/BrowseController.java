package sample;

import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class BrowseController {

    @FXML
    Button backLogin, account;

    @FXML
    VBox genreVBox;

    ArrayList<String> genres;
    Filter filter;

    public void initialize()
    {
        genres = Genres.getInstance().genres;

       for(String m: genres)
       {
           CheckBox tempCheckBox = new CheckBox(m);
           tempCheckBox.selectedProperty().addListener((v, oldValue, newValue) -> {
               SetFilters(v, newValue);
               Search();
           });
           genreVBox.getChildren().add(tempCheckBox);
       }

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


    void Search()
    {

    }

    void SetFilters(ObservableValue box, boolean val)
    {
        
    }


}
