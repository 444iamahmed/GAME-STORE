package sample;

import java.util.ArrayList;
import java.util.List;

class Inventory
{ 
    
    ArrayList<Title> ExistingTitles;
  
    Inventory()
    {
        ExistingTitles = new ArrayList<Title>();
    }
    Inventory(String filename)
    {
        

    } 
    public ArrayList<Title> filterTitles(Filter criteria)
    {
        ArrayList<Title> filtered = new ArrayList<>();
        for(int i = 0; i < ExistingTitles.size();i++)
        {
            Title temp = ExistingTitles.get(i);
            if(((temp.getName()).contains(criteria.getSearchText()) || criteria.getSearchText().isEmpty()) && 
            temp.getRating() >= criteria.getRating() && temp.getPrice() <= criteria.getMaxPrice() 
            && (temp.getGenres()).containsAll(criteria.getGenres()) && (criteria.getPlatforms()).contains(temp.getPlatform()))
            {
                filtered.add(temp);
            }
        }
        return filtered;
    }
} 