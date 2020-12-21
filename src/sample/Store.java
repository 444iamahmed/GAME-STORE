package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Store {
    private static Store instance = null;

    static Account activeAccount;
    Inventory inventory;
    ArrayList<String> genres;
    ArrayList<String> platforms;
    PersistenceDBHandler persistenceDBHandler;
    private Store()
    {
        persistenceDBHandler = MySQLHandler.getInstance();
        inventory = Inventory.getInstance();
        inventory.setPersistenceDBHandler(persistenceDBHandler);
        genres = persistenceDBHandler.getGenres();
        platforms = persistenceDBHandler.getPlatforms();
    }
    public static Store getInstance()
    {
        if (instance == null)
            instance = new Store();

        return instance;
    }

    public ObservableList<Title> search(Filter filters)
    {
        return FXCollections.observableList(inventory.search(filters));
    }
    public ObservableList<Title> getOwnedKeys()
    {
        return FXCollections.observableList(persistenceDBHandler.getOwnedKeys(activeAccount));
    }
}
