package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CustomersPageController {

    @FXML
    ToggleGroup timeCreatedToggleGroup, sortOrderToggleGroup;

    @FXML
    TextField searchText;

    Store myStore;
    Filter filter;
    public void initialize()
    {
        myStore = Store.getInstance();
        filter = new Filter();
    }

    public void search()
    {
        myStore.searchCustomers(filter);
    }
}
