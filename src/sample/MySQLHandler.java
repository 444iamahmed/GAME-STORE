package sample;

import java.sql.*;

import java.util.ArrayList;

public class MySQLHandler extends PersistenceDBHandler {

    Connection connection;

    private MySQLHandler()
    {
        try {
                connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/game-store?useSSL=false", "root", "mani2012");
            }catch (SQLException e) {
            printSQLException(e);
        }
    }
    private String arrayListQuery(String logic,String field, ArrayList<String> list)
    {
        String tempString = new String();
        if(!list.isEmpty())
        {
            tempString = field;
            tempString += logic + " IN (";
            for (String m : list)
                field += "\"" + m + "\", ";
            tempString += ")";
        }
        return tempString;
    }
    private String searchTextQuery(String logic, String matchColumns, String text)
    {
        String tempString = new String();
        if(!text.isEmpty())
            tempString = logic + " MATCH (" + matchColumns + ") AGAINST (\'" + text + "\' IN NATURAL LANGUAGE MODE)";
        return tempString;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    public static MySQLHandler getInstance()
    {
        if (instance == null)
            instance = new MySQLHandler();

        return (MySQLHandler) instance;
    }

    @Override
    public ArrayList<String> getGenres() {
        String QUERY = "select * from genre";
        ArrayList<String> genres = new ArrayList<>();
        try
            (Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(QUERY);){
            while (rs.next())
                 genres.add(rs.getString("genre_name"));

        }catch (SQLException e) {
            printSQLException(e);
        }

        return genres;
    }

    @Override
    public ArrayList<String> getPlatforms() {

        String QUERY = "select * from platform";
        ArrayList<String> platforms = new ArrayList<>();
        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
            while (rs.next())
                platforms.add(rs.getString("platform_name"));

        }catch (SQLException e) {
            printSQLException(e);
        }
        return platforms;
    }

    @Override
    public ArrayList<Title> getOwnedTitles(Account account) {
        String QUERY = "select * from title where title_key_owner = " + account.username;
        ArrayList<Title> titles = new ArrayList<>();

        try (
                Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(QUERY);
                 ){
            while (rs.next())
            {

                Title tempTitle = new Title(rs.getString("title_name"),
                        rs.getDate("title_release_date"),
                        rs.getString("title_description"),
                        rs.getString("title_developer"),
                        rs.getString("title_platform"),
                        rs.getDouble("title_rating") ,
                        rs.getDouble("title_price"));

                String GENRE_QUERY = "select title_genre from title where title_name = "+tempTitle.getName() +
                        " and title_developer = " + tempTitle.getDeveloper() +
                        " and title_platform = "+ tempTitle.getPlatform() +
                        " GROUP BY title_name,title_developer, title_platform";
                try (ResultSet genreSet = stmt.executeQuery(GENRE_QUERY)){
                    while (genreSet.next())
                        tempTitle.addGenre(genreSet.getString("title_genre"));
                } catch (SQLException e){
                    printSQLException(e);
                }
                String KEY_QUERY = "select title_key from title where title_name = "+tempTitle.getName() +
                        " AND title_developer = " + tempTitle.getDeveloper() +
                        " AND title_platform = " + tempTitle.getPlatform() +
                        " AND title_key_owner = " + account.username;

                try (ResultSet keySet = stmt.executeQuery(KEY_QUERY)){
                    while (keySet.next())
                        tempTitle.addKey(new Key(keySet.getString("title_key")));
                } catch (SQLException e){
                    printSQLException(e);
                }
                titles.add(tempTitle);
            }

        }catch (SQLException e) {
            printSQLException(e);
        }
        return titles;
    }

    @Override
    public ArrayList<Title> getTitles(Filter filter) {

        String QUERY = "select * from title where " +
                searchTextQuery("","title_name, title_developer, title_platform, title_genre, title_description", filter.getSearchText()) +
                "AND title_rating >= " + filter.getRating() + " AND title_price <= " + filter.getMaxPrice() +
                arrayListQuery("AND", "title_genre", filter.getGenres()) +
                arrayListQuery("AND",  "title_platform", filter.getPlatforms()) +
                "GROUP BY title_name,title_developer, title_platform";
        ArrayList<Title> titles = new ArrayList<>();

        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
        ){
            while (rs.next())
            {

                Title tempTitle = new Title(rs.getString("title_name"),
                        rs.getDate("title_release_date"),
                        rs.getString("title_description"),
                        rs.getString("title_developer"),
                        rs.getString("title_platform"),
                        rs.getDouble("title_rating") ,
                        rs.getDouble("title_price"));
                String GENRE_QUERY = "select title_genre from title where title_name = "+tempTitle.getName() +
                        " and title_developer = " + tempTitle.getDeveloper() +
                        " and title_platform = "+ tempTitle.getPlatform();
                try (ResultSet genreSet = stmt.executeQuery(GENRE_QUERY)){
                    while (genreSet.next())
                        tempTitle.addGenre(genreSet.getString("title_genre"));
                } catch (SQLException e){
                    printSQLException(e);
                }
                titles.add(tempTitle);
            }

        }catch (SQLException e) {
            printSQLException(e);
        }
        return titles;
    }
}
