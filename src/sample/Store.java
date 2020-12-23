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

    public boolean usernameExists(String username)
    {
        return false;
    }

    public boolean emailExists(String email)
    {
        return false;
    }

    public void saveAccountAndSetActive(String username, String email, String password)
    {
        activeAccount = persistenceDBHandler.saveAccount(username, email, password);
    }
    public boolean checkAccountAndLogin(String id, String password)
    {
        if((activeAccount = persistenceDBHandler.retrieveAccount(id, password)) != null)
            return true;
        return false;
    }
    public Account getActiveAccount()
    {
        return activeAccount;
    }
    public void saveActiveAccountChanges(Account account)
    {
        activeAccount = account;
        persistenceDBHandler.updateAccount(activeAccount);
    }
    public void deleteActiveAccount()
    {
        
    }

}
