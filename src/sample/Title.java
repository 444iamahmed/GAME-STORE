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

    public String getGenreString() {
        return genreString;
    }

    String genreString;

    public ArrayList<String> getGenre() {
        return genre;
    }

    ArrayList<String> genre;
    Float rating;
    Float price;
    //Image
    Title()
    {
        name = new String("");
        releaseDate = new Date();
        description = new String("");
        platform = new String("");
        genre = new ArrayList<>();
        rating = (float) 0;
        price = (float) 0;
        genreString = new String("");
    }

    Title(String n, Date d, String desc, String dev, ArrayList<String> g, String plat, Float r, Float p)
    {
        genreString = new String("");
        name = n;
        releaseDate = d;
        description = desc;
        developer = dev;
        genre = new ArrayList<>();
        genre.addAll(g);
        for(String m: genre)
            genreString += m + ", ";
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