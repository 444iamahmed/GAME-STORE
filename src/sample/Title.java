package sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Title extends Displayable
{


    private String name;
    private Date releaseDate;
    private String description;
    private String developer;
    private String platform;
    private ArrayList<String> genre;
    private HashSet<Key> keys;
    private Double rating;
    private Double price;
    private final PersistenceDBHandler persistenceDBHandler = MySQLHandler.getInstance();
    //Image
    public HashSet<Key> getKeys() {
        return keys;
    }


    Title()
    {
        name = "";
        releaseDate = new Date();
        description = "";
        platform = "";
        genre = new ArrayList<>();
        keys = new HashSet<>();
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
        keys = new HashSet<>();
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
        genre = new ArrayList<>();
        keys = new HashSet<>();

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

    public Double getRating()
    {
        return rating;
    }

    public Double getPrice()
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
    public void removeKey(Key key){keys.remove(key);}
    public boolean equals(Title title)
    {
        return name.equals(title.name) && platform.equals(title.platform) && developer.equals(title.developer);
    }

    public void updateGenres(ArrayList<String> genres)
    {
        genre.clear();
        genre.addAll(genres);
    }

    public String getGenreString() {
        String tempGenres = "";
        for(String i: genre)
            tempGenres += i + " ";
        return tempGenres;
    }

    public void fillKeys() {
        keys = persistenceDBHandler.getTitleKeys(name, developer, platform);
    }
}