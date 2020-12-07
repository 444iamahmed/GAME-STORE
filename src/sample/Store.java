package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Store {
    private static Store single_instance = null;

    Inventory inventory;
    Store()
    {
        inventory = new Inventory();
    }
    public static Store getInstance()
    {
        if (single_instance == null)
            single_instance = new Store();

        return single_instance;
    }

    public ObservableList<Title> Search(Filter filters)
    {
        return FXCollections.observableList(inventory.filterTitles(filters));
    }

}
