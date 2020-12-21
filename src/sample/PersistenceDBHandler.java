package sample;

import java.util.ArrayList;

public abstract class PersistenceDBHandler {

    protected static PersistenceDBHandler instance = null;

    public abstract ArrayList<String> getGenres();
    public abstract ArrayList<String> getPlatforms();
    public abstract ArrayList<Title> getTitles(Filter filter);
    public abstract ArrayList<Title> getOwnedKeys(Account account);

}
