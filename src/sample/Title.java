package sample;

import java.util.ArrayList;
import java.util.Date;

public class Title extends Displayable
{

    private String name;
    private Date releaseDate;
    private String description;
    private String developer;
    private String platform;
    private ArrayList<String> genre;
    private ArrayList<Key> keys;
    private Double rating;
    private Double price;
    //Image
    public ArrayList<Key> getKeys() {
        return keys;
    }


    Title()
    {
        name = new String("");
        releaseDate = new Date();
        description = new String("");
        platform = new String("");
        genre = new ArrayList<>();
        rating = 0.0;
        price = 0.0;
    }

    Title(String n, Date d, String desc, String dev, ArrayList<String> g, String plat, Double r, Double p)
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
    Title(String n, Date d, String desc, String dev, String plat, Double r, Double p)
    {
        name = n;
        releaseDate = d;
        description = desc;
        developer = dev;
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

    public double getRating()
    {
        return rating;
    }

    public double getPrice()
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

    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void addGenre(String g)
    {
        genre.add(g);
    }
    public void addKey(Key key){keys.add(key);}
    public void updateGenres(ArrayList<String> genres)
    {
        genre.clear();
        genre.addAll(genres);
    }
} 