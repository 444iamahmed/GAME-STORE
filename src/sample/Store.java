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
    public ObservableList<Order> getOwnedKeys()
    {
        return FXCollections.observableList(persistenceDBHandler.getOrders(activeAccount));
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
    public boolean checkAccountAndLoginCustomer(String id, String password)
    {
        return (activeAccount = persistenceDBHandler.retrieveAccountCustomer(id, password)) != null;

    }
    public boolean checkAccountAndLoginAdmin(String id, String password)
    {
        return (activeAccount = persistenceDBHandler.retrieveAccountAdmin(id, password)) != null;
    }

    public Account getActiveAccount()
    {
        return activeAccount;
    }
    public void saveAccountChangesCustomer(Account account)
    {
        if(activeAccount.getEmail() == account.getEmail())
            activeAccount = account;
        persistenceDBHandler.updateCustomerAccount(account);
    }

    public void saveAccountChangesAdmin(Account account)
    {
        if(activeAccount.getEmail() == account.getEmail())
            activeAccount = account;
        persistenceDBHandler.updateAdminAccount(account);
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
        return cart.add(title);
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

    public ArrayList<Order> getOrders() {
        return persistenceDBHandler.getOrders(activeAccount);
    }
}
