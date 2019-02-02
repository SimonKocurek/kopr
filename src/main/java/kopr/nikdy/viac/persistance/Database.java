package kopr.nikdy.viac.persistance;

import kopr.nikdy.viac.entities.ParkingTicket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class Database {

    private static final String DATABASE_NAME = "parking_system";

    private static Connection connection;

    public static void initialize() throws ClassNotFoundException, SQLException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME + ".db");
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e);
        }
    }

    private static void checkDatabaseInitialized() throws DatabaseNotInitializedException {
        if (connection == null) {
            throw new DatabaseNotInitializedException();
        }
    }

    private static void addTicket(ParkingTicket ticket) throws DatabaseNotInitializedException {
        checkDatabaseInitialized();

//        Statement statement = connection.createStatement();
//        statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//        statement.executeUpdate("drop table if exists person");
//            statement.executeUpdate("create table person (id integer, name string)");
//            statement.executeUpdate("insert into person values(1, 'leo')");
//            statement.executeUpdate("insert into person values(2, 'yui')");
//            ResultSet rs = statement.executeQuery("select * from person");
//            while(rs.next())
//            {
//                // read the result set
//                System.out.println("name = " + rs.getString("name"));
//                System.out.println("id = " + rs.getInt("id"));
//            }

    }

    private static void remoteTicket(UUID id) throws DatabaseNotInitializedException {
        checkDatabaseInitialized();
    }

}
