import Title.java;
import Filter.java;

class Inventory
{ 
    
    List<Title> ExistingTitles;
  
    Inventory()
    {   }
    Inventory(String filename)
    {
        

    } 
    public List<Title> filterTitles(Filter criteria)
    {
        List<Title> filtered;
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