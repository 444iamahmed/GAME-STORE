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
                    && (temp.getGenres()).containsAll(criteria.getGenres()) && (criteria.getPlatforms()).contains(temp.getPlatform())) {
                filtered.add(temp);
            }
        }
        return filtered;
    }
} 