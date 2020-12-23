package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import java.lang.Object;


public class LoginController {

    @FXML Button login;

    @FXML
    TextField loginId, loginPassword;

    @FXML
    Label loginError;

    Store myStore;
    void initialize()
    {
        myStore = Store.getInstance();
    }

    public void validateCredentials() throws IOException {
        if(myStore.checkAccountAndLogin(loginId.getText(), loginPassword.getText()))
            changeSceneToBrowse();
        else
            loginError.setText("Invalid username or password!");
    }

    public void changeSceneToBrowse() throws IOException
    {
        Parent browseParent = FXMLLoader.load(getClass().getResource("Browse.fxml"));
        Scene browseScene = new Scene(browseParent);

        Stage window = (Stage) login.getScene().getWindow();
        window.setScene(browseScene);
    }


}
