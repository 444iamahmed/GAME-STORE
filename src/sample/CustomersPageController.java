package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;

public class CustomersPageController extends UsersPageController{

    @FXML
    TableView customersContainer;

    @Override
    public void initialize() throws IOException {
        super.initialize();
        fillCustomersContainer();
    }

    private void fillCustomersContainer() {
        TableColumn<Account, String> usernameColumn = new TableColumn<>("Name");
        usernameColumn.setMinWidth(200);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Account, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Account, Date> dateCreatedColumn = new TableColumn<>("Date Created");
        dateCreatedColumn.setMinWidth(200);
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        customersContainer.getColumns().addAll(usernameColumn, emailColumn, dateCreatedColumn);
    }

    @Override
    public void search() {
        customersContainer.setItems(myStore.searchCustomers(filter));
    }
}
