package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Store {
    private static Store instance = null;

    private Account activeAccount;
    private Inventory inventory;
    private Cart cart;
    private ArrayList<String> genres;
    private ArrayList<String> platforms;
    private PersistenceDBHandler persistenceDBHandler;
    private Payment paymentHandler;
    private Store()
    {
        persistenceDBHandler = MySQLHandler.getInstance();
        inventory = Inventory.getInstance();
        cart = Cart.getInstance();
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

    public ArrayList<String> getGenres() {
        return genres;
    }

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public ObservableList<Title> searchTitles(BrowseFilter filters)
    {
        return FXCollections.observableList(inventory.search(filters));
    }
    public ObservableList<Title> getOwnedKeys()
    {
        return FXCollections.observableList(persistenceDBHandler.getOwnedKeys(activeAccount));
    }

    public boolean usernameExists(String username)
    {
        return persistenceDBHandler.checkUserExistence(username);
    }

    public boolean emailExists(String email)
    {
        return persistenceDBHandler.checkEmailExistence(email);
    }

    public void saveAccountAndSetActiveCustomer(String username, String email, String password)
    {
        activeAccount = persistenceDBHandler.saveAccountCustomer(username, email, password);
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
    public void saveAccountChanges(Account account)
    {
        if(activeAccount.getEmail() == account.getEmail())
            activeAccount = account;
        persistenceDBHandler.updateCustomerAccount(activeAccount);
    }


    public void deleteCustomerAccount(Account account)
    {
        if(account.getEmail() == activeAccount.getEmail())
            activeAccount = null;
        persistenceDBHandler.deleteCustomerAccount(account);
    }

    public void deleteAdminAccount(Account account)
    {
        if(account.getEmail() == activeAccount.getEmail())
            activeAccount = null;
        persistenceDBHandler.deleteCustomerAccount(account);
    }


    public void removeFromCart(Title title)
    {
        cart.remove(title);
    }
    public boolean addToCart(Title title)
    {
        if(title.getKeys().isEmpty())
            return false;
        cart.add(title);
        return true;
    }
    public ArrayList<CartItem> getCartItems()
    {
        return cart.getItems();
    }
    public Double getCartTotal()
    {
        return cart.getTotal();
    }
    public void checkout(String cardNumber, String expiration, String CVV)
    {

    }
    public Double generateOrderNumber()
    {
        return 0.0;
    }
    public void clearCart()
    {
        cart.clear();
    }
    public void signOut()
    {
        activeAccount = null;
    }


    public ObservableList<Account> searchCustomers(Filter filter) {
        return FXCollections.observableList(persistenceDBHandler.getCustomers(filter));
    }
    public ObservableList<Account> searchAdmins(Filter filter) {
        return FXCollections.observableList(persistenceDBHandler.getAdmins(filter));
    }

    public int getAdminCount() {
        return persistenceDBHandler.getAdminCount();
    }

    public Title saveTitleChanges(String originalName, String originalDeveloper, String originalPlatform, Title changedTitle) {
        return inventory.saveTitleChanges(originalName, originalDeveloper, originalPlatform, changedTitle);
    }
}
