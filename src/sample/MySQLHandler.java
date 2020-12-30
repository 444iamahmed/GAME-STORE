package sample;

import java.sql.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

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
        StringBuilder tempString = new StringBuilder();
        if(!list.isEmpty())
        {
            tempString = new StringBuilder(logic + " ");
            tempString.append(field).append(" IN (");
            for (int i = 0; i<list.size(); i++)
            {
                tempString.append("\"").append(list.get(i)).append("\"");
                if(i != list.size() - 1)
                    tempString.append(", ");
            }
            tempString.append(") ");
        }
        return tempString.toString();
    }
    private String searchTextQuery(String logic, String matchColumns, String text, String postLogic)
    {
        String tempString = "";
        if(!text.isEmpty())
            tempString = logic + " MATCH (" + matchColumns + ") AGAINST ('" + text + "' IN NATURAL LANGUAGE MODE) " + postLogic + " ";
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
        String QUERY = "select * from gka5gkdoler1i5f1.keys where key_owner = \"" + account.getUsername() + "\"";
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
                                titleInformation.getDate("title_release_date").toLocalDate(),
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
    public ArrayList<Title> getTitles(BrowseFilter browseFilter) {

        String QUERY = "select * from title where " +
                searchTextQuery("","title.title_name, title.title_developer, title.title_platform, title.title_description", browseFilter.getSearchText(),"AND")  +
                "title.title_rating >= " + browseFilter.getRating() + " AND title.title_price <= " + browseFilter.getMaxPrice() +
                " AND (select count(title_genre.genre) from title_genre where " +
                "title_genre.title_name = title.title_name " +
                "AND title_genre.title_developer = title.title_developer " +
                "AND title_genre.title_platform = title.title_platform " +
                arrayListQuery("AND", "title_genre.genre", browseFilter.getGenres()) +
                ") > 0 "+
                arrayListQuery(" AND",  "title.title_platform", browseFilter.getPlatforms());
        ArrayList<Title> titles = new ArrayList<>();

        try (
                Statement titlesStatement = connection.createStatement();
                ResultSet rs = titlesStatement.executeQuery(QUERY);
                ){
            while (rs.next())
            {

                Title tempTitle = new Title(rs.getString("title_name"),
                        rs.getDate("title_release_date").toLocalDate(),
                        rs.getString("title_description"),
                        rs.getString("title_developer"),
                        rs.getString("title_platform"),
                        rs.getDouble("title_rating") ,
                        rs.getDouble("title_price"));

                String KEY_QUERY = "select * from gka5gkdoler1i5f1.keys where title_name = \"" + tempTitle.getName() + "\" " +
                        "AND title_developer = \"" + tempTitle.getDeveloper() + "\" " +
                        "AND title_platform = \"" + tempTitle.getPlatform() + "\"";
                try (
                        Statement keysStatement = connection.createStatement();
                        ResultSet keysSet = keysStatement.executeQuery(KEY_QUERY);
                ){
                    while (keysSet.next())
                        tempTitle.addKey(new Key(keysSet.getString("key")));
                } catch (SQLException e){
                    printSQLException(e);
                }

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

    @Override
    public Account saveAccountCustomer(String username, String email, String password) {
        String QUERY = "INSERT INTO customer (customer_username, customer_password, customer_email) VALUES (\"" + username + "\", \"" + password + "\", \"" + email + "\")";
        Account saved = new Account(username,email,password);
        try
                (Statement stmt = connection.createStatement();

                 ){
            int rowsUpdated = stmt.executeUpdate(QUERY);
            return retrieveAccountCustomer(username, password);
        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    @Override
    public Account retrieveAccountCustomer(String id, String password) {
        String QUERY = "select * from customer where customer.customer_username = \"" + id + "\"OR customer.customer_email = \"" + id + "\"";
        Account retrieved = new Account();
        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
            if(!rs.next())
                return null;

            String correspondingPassword = rs.getString("customer_password");
            if(!password.equals(correspondingPassword))
                return null;

            retrieved.setUsername(rs.getString("customer_username"));
            retrieved.setEmail(rs.getString("customer_email"));
            retrieved.setPassword(correspondingPassword);

        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
        return retrieved;
    }

    @Override
    public Account retrieveAccountAdmin(String id, String password) {
        String QUERY = "select * from admin where admin.admin_username = \"" + id + "\"OR admin.admin_email = \"" + id + "\"";
        Account retrieved = new Account();
        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
            if(!rs.next())
                return null;

            String correspondingPassword = rs.getString("admin_password");
            if(!password.equals(correspondingPassword))
                return null;

            retrieved.setUsername(rs.getString("admin_username"));
            retrieved.setEmail(rs.getString("admin_email"));
            retrieved.setPassword(correspondingPassword);

        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
        return retrieved;
    }

    @Override
    public Boolean checkUserExistence(String username) {
        String QUERY = "select * from customer where customer.customer_username = \"" + username + "\"";

        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
            if(rs.next()) {
               return true;
            }
        }catch (SQLException e) {
            printSQLException(e);
            return false;
        }
        return false;
    }

    @Override
    public Boolean checkAdminExistence(String username) {
        String QUERY = "select * from admin where admin.admin_username = \"" + username + "\"";

        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
            if(rs.next()) {
                return true;
            }
        }catch (SQLException e) {
            printSQLException(e);
            return false;
        }
        return false;
    }

    @Override
    public Boolean checkEmailExistence(String email) {
        String QUERY = "select * from customer where customer.customer_email = \"" + email + "\"";

        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
            if(rs.next()) {
                return true;
            }
        }catch (SQLException e) {
            printSQLException(e);
            return false;
        }
        return false;
    }

    @Override
    public Title getSingleTitle(String title_name) {
        String QUERY = "select * from title where title.title_name = \"" + title_name + "\"";

        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
            if(rs.next()) {
                Title tempTitle = new Title(rs.getString("title_name"),
                        rs.getDate("title_release_date").toLocalDate(),
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
                    return tempTitle;
                }
                return tempTitle;
            }
        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
        return null;
    }

    @Override
    public void updateCustomerAccount(Account account) {
        String QUERY = "UPDATE customer SET  customer_password = \"" + account.getPassword() + "\", customer_username = \"" + account.getUsername() + "\" WHERE (customer_email =  \"" + account.getEmail() + "\")";
        try
                (Statement stmt = connection.createStatement();

                 ){
            int rowsUpdated = stmt.executeUpdate(QUERY);
        }catch (SQLException e) {
            printSQLException(e);

        }

    }
    @Override
    public void updateAdminAccount(Account account) {
        String QUERY = "UPDATE admin SET  admin_password = \"" + account.getPassword() + "\", admin_username = \"" + account.getUsername() + "\" WHERE (admin_email =  \"" + account.getEmail() + "\")";
        try
                (Statement stmt = connection.createStatement();

                ){
            int rowsUpdated = stmt.executeUpdate(QUERY);
        }catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public void deleteAdminAccount(Account account) {
        String QUERY = "DELETE from admin where admin.admin_email = \"" + account.getEmail() + "\"";

        try (Statement stmt = connection.createStatement();){
            int rowsUpdated = stmt.executeUpdate(QUERY);
        }catch (SQLException e) {
            printSQLException(e);
        }
    }


    @Override
    public void deleteCustomerAccount(Account account) {
        String QUERY = "DELETE from customer where customer.customer_email = \"" + account.getEmail() + "\"";

        try (Statement stmt = connection.createStatement();){
            int rowsUpdated = stmt.executeUpdate(QUERY);
        }catch (SQLException e) {
            printSQLException(e);

        }

    }

    @Override
    public ArrayList<Account> getCustomers(Filter filter) {
        LocalDate date = LocalDate.now();
        TimePeriod period = filter.getTimePeriod();
        switch (period)
        {
            case THIS_YEAR -> date = date.with(TemporalAdjusters.firstDayOfYear());
            case THIS_MONTH -> date = date.with(TemporalAdjusters.firstDayOfMonth());
            case THIS_WEEK -> date = date.with(WeekFields.of(Locale.getDefault()).dayOfWeek(),1);
            default -> date = LocalDate.EPOCH;
        }


        String QUERY = "select * from customer where " + searchTextQuery("","customer.customer_username, customer.customer_email", filter.getSearchText(),"AND") +
                "date_created >= ? order by  date_created " + filter.getOrder();
        ArrayList<Account> accounts = new ArrayList<>();
        try
        {
            PreparedStatement stmt = connection.prepareStatement(QUERY);
            stmt.setTimestamp(1, Timestamp.valueOf(date.atStartOfDay()));

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Account tempAcc = new Account(rs.getString("customer_username"),
                        rs.getString("customer_email"),
                        rs.getString("customer_password"));
                tempAcc.setDateCreated(rs.getDate("date_created"));
                accounts.add(tempAcc);
            }
            return accounts;
        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    @Override
    public ArrayList<Account> getAdmins(Filter filter) {

        LocalDate date = LocalDate.now();
        TimePeriod period = filter.getTimePeriod();
        switch (period)
        {
            case THIS_YEAR -> date = date.with(TemporalAdjusters.firstDayOfYear());
            case THIS_MONTH -> date = date.with(TemporalAdjusters.firstDayOfMonth());
            case THIS_WEEK -> date = date.with(WeekFields.of(Locale.getDefault()).dayOfWeek(),1);
            default -> date = LocalDate.EPOCH;
        }

        String QUERY = "select * from admin where " + searchTextQuery("","admin.admin_username, customer.admin_email", filter.getSearchText(),"AND") +
                "date_created >= ? order by  date_created " + filter.getOrder();
        ArrayList<Account> accounts = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(QUERY)){
            stmt.setTimestamp(1, Timestamp.valueOf(date.atStartOfDay()));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Account tempAcc = new Account(rs.getString("admin_username"),
                        rs.getString("admin_email"),
                        rs.getString("admin_password"));
                tempAcc.setDateCreated(rs.getDate("date_created"));
                accounts.add(tempAcc);
            }
            return accounts;
        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    @Override
    public int getAdminCount(){
        String QUERY = "select COUNT(*) from admin";
        try
                (Statement stmt = connection.createStatement();

                 ResultSet rs = stmt.executeQuery(QUERY);){
                while(rs.next()){
                    return rs.getInt(1);
                }
        }catch (SQLException e) {
            printSQLException(e);
            return 0;
        }
        return 0;
    }

    @Override
    public HashSet<Key> getTitleKeys(String name, String developer, String platform) {

        String QUERY = "select * from gka5gkdoler1i5f1.keys where title_name = \"" + name + "\" " +
                "AND title_developer = \"" + developer + "\" " +
                "AND title_platform = \"" + platform + "\"";

        HashSet<Key> keys = new HashSet<>();


        try (
                Statement titlesStatement = connection.createStatement();
                ResultSet rs = titlesStatement.executeQuery(QUERY);
        ){
            while (rs.next())
            {
                keys.add(new Key(rs.getString("key")));
            }

        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
        return keys;
    }

    @Override
    public ArrayList<Order> getOrders(Account account) {

        String QUERY = "select * from orders where customer_email = \"" + account.getEmail() + "\"";

        ArrayList<Order> orders = new ArrayList<Order>();


        try (
                Statement titlesStatement = connection.createStatement();
                ResultSet rs = titlesStatement.executeQuery(QUERY);
        ){
            while (rs.next())
            {
                Order tempOrder = new Order();
                tempOrder.setOrderNumber(rs.getInt("order_id"));
                tempOrder.setTotal(rs.getDouble("price"));
                //tempOrder.setTitles(getOwnedKeys(tempOrder));
                orders.add(tempOrder);
            }

        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
        return orders;
    }

    @Override
    public Title updateTitle(String oldName, String oldDeveloper, String oldPlatform, Title newTitle) {
        String QUERY = "UPDATE title SET title_name = '" + newTitle.getName() + "', title_developer = '" + newTitle.getDeveloper() + "', title_platform = '" + newTitle.getPlatform() +
                "', title_release_date = '" + Timestamp.valueOf(newTitle.getReleaseDate().atStartOfDay()) + "', title_description = '" + newTitle.getDescription() + "', title_price = " + newTitle.getPrice() + ", title_rating = " + newTitle.getRating() +
                " WHERE (title.title_name =  '" + oldName + "' AND title.title_developer = '" + oldDeveloper + "' AND title.title_platform = '" + oldPlatform + "')";

        try (Statement updateStatement = connection.createStatement()){
            int rowsUpdatedInTitles = updateStatement.executeUpdate(QUERY);

            String QUERY2 = "DELETE from title_genre WHERE (title_name =  '" + newTitle.getName() + "' AND title_developer = '" + newTitle.getDeveloper() + "'AND title_platform = '" + newTitle.getPlatform() + "')";

            try (Statement deleteGenreStatement = connection.createStatement();) {
                int rowsUpdatedInGenresDeletion = updateStatement.executeUpdate(QUERY2);
                for(int i = 0; i < newTitle.getGenre().size(); i++) {
                String QUERY3 = "INSERT INTO title_genre (title_name, title_developer, title_platform, genre) VALUES ('" + newTitle.getName() + "', '" + newTitle.getDeveloper() + "', '" + newTitle.getPlatform() + "', '" + newTitle.getGenre().get(i) + "')" ;

                try (
                        Statement genreStatement = connection.createStatement();

                ) {

                    int rowsUpdatedInGenresUpdation = genreStatement.executeUpdate(QUERY3);
                } catch (SQLException e) {
                    printSQLException(e);
                    return null;
                }
            }
                String QUERY4 = "DELETE from title_genre WHERE (title_name =  '" + newTitle.getName() + "' AND title_developer = '" + newTitle.getDeveloper() + "'AND title_platform = '" + newTitle.getPlatform() + "')";

                try (
                        Statement deleteKeysStatement = connection.createStatement();
                        ResultSet rs4 = deleteKeysStatement.executeQuery(QUERY4);
                ) {
                    for(Key j: newTitle.getKeys()) {
                        String QUERY5 = "INSERT INTO keys (title_name, title_developer, title_platform, key) VALUES ('" + newTitle.getName() + "', '" + newTitle.getDeveloper() +
                                "', '" + newTitle.getPlatform() + "', '" + j.getValue() + "')" ;

                        try (
                                Statement keysStatement = connection.createStatement();
                                ResultSet rs5 = keysStatement.executeQuery(QUERY5);
                        ) {


                        } catch (SQLException e) {
                            printSQLException(e);
                            return null;
                        }
                    }
                    return newTitle;
                }catch (SQLException e) {
                    printSQLException(e);
                    return null;
                }
            }catch (SQLException e) {
                printSQLException(e);
                return null;
            }


        }catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    @Override
    public Integer saveOrder(Order order, Account account) {
        String QUERY = "INSERT INTO order (total, customer_email) VALUES (\"" + order.getTotal() + "\", \"" + account.getEmail() + "\")";


        try (
                Statement titlesStatement = connection.createStatement();
                //ResultSet rs = titlesStatement.executeQuery(QUERY);
        ) {

            titlesStatement.executeUpdate(QUERY);
            String QUERY2 = "select * from orders where customer_email = \"" + account.getEmail() + "\"";
            int orderID = 0;


            try (
                    Statement lastOrderStatement = connection.createStatement();
                    ResultSet rs = lastOrderStatement.executeQuery(QUERY2);
            ){
                while (rs.next())
                {
                    orderID = rs.getInt("orderid");
                }

            }catch (SQLException e) {
                printSQLException(e);
                return null;
            }
            order.setOrderNumber(orderID);
            for (Title j: order.getTitles()
                 ) {
                for (Key i: j.getKeys()
                     ) {
                    String QUERY3 = "UPDATE key SET orderid = (\"" + orderID + "\") WHERE (key = \"" + i.getValue() + "\")";

                    try (
                            Statement keysStatement = connection.createStatement();
                            //ResultSet rs = titlesStatement.executeQuery(QUERY);
                    ) {
                        keysStatement.executeUpdate(QUERY3);
                    } catch (SQLException e) {
                        printSQLException(e);
                        return null;
                    }
                }
            }
            return order.getOrderNumber();
        } catch (SQLException e) {
            printSQLException(e);
            return null;
        }

    }
    public ArrayList<Order> getOrders() {
        return new ArrayList<>();
    }

}
