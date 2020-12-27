package sample;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class PersistenceDBHandler {

    protected static PersistenceDBHandler instance = null;

    public abstract ArrayList<String> getGenres();
    public abstract ArrayList<String> getPlatforms();
    public abstract ArrayList<Title> getTitles(BrowseFilter browseFilter);
    public abstract ArrayList<Title> getOwnedKeys(Account account);
    public abstract Account saveAccountCustomer(String username, String email, String password);
    public abstract Account retrieveAccount(String username, String password);
    public abstract Account retrieveAdmin(String username, String password);
    public abstract Title getSingleTitle(String title_name);
    public abstract Boolean checkUserExistence(String username);
    public abstract Boolean checkAdminExistence(String username);
    public abstract Boolean checkEmailExistence(String email);
    public abstract void updateCustomerAccount(Account account);
    public abstract void updateAdminAccount(Account account);
    public abstract ArrayList<Account> getCustomers(Filter filter);
    public abstract ArrayList<Account> getAdmins(Filter filter);

    public abstract void deleteCustomerAccount(Account account);
    public abstract void deleteAdminAccount(Account account);

    public abstract int getAdminCount();

    public abstract HashSet<Key> getTitleKeys(String name, String developer, String platform);

    public abstract Title updateTitle(String oldName, String oldDeveloper, String oldPlatform, Title newTitle);
}
