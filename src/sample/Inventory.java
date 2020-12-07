package sample;

import java.util.ArrayList;

public class Inventory
{ 
    
    ArrayList<Title> ExistingTitles;
    TitlesDBHandler titlesDB;
    Inventory(String filename)
    {
        titlesDB = new TitlesDBHandler(filename);
        ExistingTitles = titlesDB.getTitles();

    } 
    public ArrayList<Title> filterTitles(Filter criteria)
    {
        ArrayList<Title> filtered = new ArrayList<>();
        for (Title temp : ExistingTitles) {
            if (((temp.getName()).contains(criteria.getSearchText()) || criteria.getSearchText().isEmpty()) &&
                    temp.getRating() >= criteria.getRating() && temp.getPrice() <= criteria.getMaxPrice()
                    && ((temp.getGenre()).containsAll(criteria.getGenres()) || criteria.getGenres().isEmpty()) && ((criteria.getPlatforms()).contains(temp.getPlatform())) || criteria.getPlatforms().isEmpty()) {
                filtered.add(temp);
            }
        }
        return filtered;
    }
} 