package sample;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import java.util.ArrayList;

public class MySQLHandler extends PersistenceDBHandler {

    Connection connection;

    private MySQLHandler()
    {
        try {
                connection = DriverManager
                        .getConnection("jdbc:mysql://z3iruaadbwo0iyfp.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/gka5gkdoler1i5f1?useSSL=false&zeroDateTimeBehavior =convertToNull", "p9uy9lzjrzjk4bgr", "kcr96eiqdzrgoiu7");
            }catch (SQLException e) {
            printSQLException(e);
        }
    }
    private String arrayListQuery(String logic,String field, ArrayList<String> list)
    {
        String tempString = "";
        if(!list.isEmpty())
        {
            tempString = field;
            tempString += logic + " IN (";
            for (String m : list)
                field += "\"" + m + "\", ";
            tempString += ") ";
        }
        return tempString;
    }
    private String searchTextQuery(String logic, String matchColumns, String text, String postLogic)
    {
        String tempString = "";
        if(!text.isEmpty())
            tempString = logic + " MATCH (" + matchColumns + ") AGAINST (\'" + text + "\' IN NATURAL LANGUAGE MODE) " + postLogic + " ";
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
    public ArrayList<Title> getOwnedKeys(Account account) {
        String QUERY = "select * from key where key_owner = " + account.username;
        Title tempTitle = null;
        Title currKeyTitle = null;
        ArrayList<Title> titles = new ArrayList<>();


        try (
                Statement titlesStatement = connection.createStatement();
                 ResultSet rs = titlesStatement.executeQuery(QUERY);
                 ){
            while (rs.next())
            {
                String TITLE_INFORMATION_QUERY = "select * from title where title_name = " + rs.getString("title_name") +
                        " AND title_developer = " + rs.getString("title_developer") +
                        " AND title_platform = " + rs.getString("title_platform");
                try (
                        Statement titleInfoStatement = connection.createStatement();
                        ResultSet titleInformation = titleInfoStatement.executeQuery(TITLE_INFORMATION_QUERY);
                        ){
                    while (titleInformation.next())
                    {
                        currKeyTitle = new Title(titleInformation.getString("title_name"),
                                titleInformation.getDate("title_release_date"),
                                titleInformation.getString("title_description"),
                                titleInformation.getString("title_developer"),
                                titleInformation.getString("title_platform"),
                                titleInformation.getDouble("title_rating") ,
                                titleInformation.getDouble("title_price"));
                    }
                } catch (SQLException e){
                    printSQLException(e);
                }

                boolean addFlag = false;
                if(tempTitle == null || (addFlag = (tempTitle != null && !tempTitle.equals(currKeyTitle))))
                {
                    if(addFlag)
                        titles.add(tempTitle);
                    tempTitle = currKeyTitle;

                    String GENRE_QUERY = "select genre from title_genre where title_name = "+tempTitle.getName() +
                        " and title_developer = " + tempTitle.getDeveloper() +
                        " and title_platform = "+ tempTitle.getPlatform() +
                        " GROUP BY title_name,title_developer, title_platform";
                    try (
                            Statement genresStatement = connection.createStatement();
                            ResultSet genreSet = genresStatement.executeQuery(GENRE_QUERY);
                            ){
                        while (genreSet.next())
                            tempTitle.addGenre(genreSet.getString("genre"));
                    } catch (SQLException e){
                        printSQLException(e);
                    }
                }

                tempTitle.addKey(new Key(rs.getString("key")));
            }

        }catch (SQLException e) {
            printSQLException(e);
        }
        return titles;
    }

    @Override
    public ArrayList<Title> getTitles(Filter filter) {

        String QUERY = "select * from title where " +
                searchTextQuery("","title.title_name, title.title_developer, title.title_platform, title.title_description", filter.getSearchText(),"AND")  +
                "title.title_rating >= " + filter.getRating() + " AND title.title_price <= " + filter.getMaxPrice() +
                "AND (select count(title_genre.genre) from title_genre where " +
                "title_genre.title_name = title.title_name " +
                "AND title_genre.title_developer = title.title_developer " +
                "AND title_genre.title_platform = title.title_platform " +
                arrayListQuery("AND", "title_genre.genre", filter.getGenres()) +
                ") > 0 "+
                arrayListQuery(" AND",  "title.title_platform", filter.getPlatforms());
        ArrayList<Title> titles = new ArrayList<>();

        try (
                Statement titlesStatement = connection.createStatement();
                ResultSet rs = titlesStatement.executeQuery(QUERY);
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
                String GENRE_QUERY = "select genre from title_genre where title_name = \""+ tempTitle.getName() +
                        "\" and title_developer = \"" + tempTitle.getDeveloper() +
                        "\" and title_platform = \""+ tempTitle.getPlatform() + "\"";
                try (
                        Statement genresStatement = connection.createStatement();
                        ResultSet genreSet = genresStatement.executeQuery(GENRE_QUERY);
                        ){
                    while (genreSet.next())
                        tempTitle.addGenre(genreSet.getString("genre"));
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
