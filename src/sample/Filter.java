package sample;

import java.util.ArrayList;
import java.util.Date;

public class Filter {

    String searchText;
    Float rating;
    ArrayList<String> genres, platforms;
    Float maxPrice;

    Filter()
    {
        genres = new ArrayList<>();
        platforms = new ArrayList<>();
        searchText = new String("");
        rating = (float) 0;
        maxPrice = Float.POSITIVE_INFINITY;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    Date releaseDate;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
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


}
