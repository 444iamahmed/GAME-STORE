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
    public void removeFromCart(CartItem cartItem)
    {
        cart.remove(cartItem);
    }
    public void addToCart(Title title)
    {
        cart.add(title);
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

}
