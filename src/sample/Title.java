package sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return name.equals(title.name) && developer.equals(title.developer) && platform.equals(title.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, developer, platform);
    }

    Title(Title title)
    {
        name = title.name;
        releaseDate = title.releaseDate;
        description = title.description;
        developer = title.developer;
        platform = title.platform;
        rating = title.rating;
        price = title.price;
        genre = new ArrayList<>();
        genre.addAll(title.getGenre());
        keys = new HashSet<>();
        keys.addAll(title.getKeys());
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

    public HashSet<Key> getKeys() {
        return keys;
    }
    public void addKey(Key key){keys.add(key);}

    public void removeKey(Key key){keys.remove(key);}



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
    public Key popKey(){
        if (keys.size() == 0) {
            return null;
        }
        Key key = keys.iterator().next();
        keys.remove(key);
        return key;
    }
}