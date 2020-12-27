package sample;

import java.util.ArrayList;

public class Inventory
{ 
    public static Inventory instance = null;

    public void setPersistenceDBHandler(PersistenceDBHandler persistenceDBHandler) {
        this.persistenceDBHandler = persistenceDBHandler;
    }

    PersistenceDBHandler persistenceDBHandler;
    private Inventory()
    {
    }
    public static Inventory getInstance()
    {
        if(instance == null)
            instance = new Inventory();
        return instance;
    }


    public ArrayList<Title> search(BrowseFilter criteria)
    {
        return persistenceDBHandler.getTitles(criteria);
    }
} 