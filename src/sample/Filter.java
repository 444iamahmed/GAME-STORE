package sample;

import java.util.ArrayList;
import java.util.Date;

public class Filter {

    private static Filter instance = null;
    private String searchText;
    private Double rating;
    private ArrayList<String> genres;
    private ArrayList<String> platforms;
    private Double maxPrice;
    private SortBy sortBy;
    private Released released;
    private String orderBy;

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public Released getReleased() {
        return released;
    }

    public void setReleased(Released released) {
        this.released = released;
    }

    private Filter()
    {
        genres = new ArrayList<>();
        platforms = new ArrayList<>();
        rating = 0.0;
        maxPrice = 500000.0;
        searchText = "";
        sortBy = SortBy.DATE;
        released = Released.ALL_TIME;
        orderBy = "asc";
    }
    public static Filter getInstance()
    {
        if(instance == null)
            instance = new Filter();
        return instance;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    private Date releaseDate;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void addGenre(String genre)
    {
        if(!genres.contains(genre))
            genres.add(genre);
    }
    public void removeGenre(String genre)
    {
        genres.remove(genre);
    }


    public void addPlatform(String platform) {
        if(!platforms.contains(platform))
            platforms.add(platform);
    }

    public void removePlatform(String platform) {
        platforms.remove(platform);
    }

    public void setOrderBy(String ob) {
        orderBy = ob;
    }
}
