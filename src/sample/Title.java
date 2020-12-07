package sample;

import java.util.ArrayList;
import java.util.Date;

public class Title 
{ 
    
    String name;
    Date releaseDate;
    String description;
    String developer;
    String platform;
    ArrayList<String> genre;
    Float rating;
    Float price;
    //Image
  
    Title(String n, Date d, String desc, String dev, ArrayList<String> g, String plat, Float r, Float p)
    {
        name = n;
        releaseDate = d;
        description = desc;
        developer = dev;
        genre = new ArrayList<>();
        genre.addAll(g);
        platform = plat;
        rating = r;
        price = p;
    } 

    public String getName()
    {
        return name;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }

    public String getPlatform()
    {
        return platform;
    }

    public String getDescription()
    {
        return description;
    }

    public Float getRating()
    {
        return rating;
    }

    public Float getPrice()
    {
        return price;
    }

    public ArrayList<String> getGenres()
    {
        return genre;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setRating(float rating)
    {
        this.rating = rating;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public void updateGenres(ArrayList<String> genres)
    {
        genre.clear();
        genre.addAll(genres);
    }
} 