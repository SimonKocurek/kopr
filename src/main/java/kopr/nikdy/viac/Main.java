package kopr.nikdy.viac;

import kopr.nikdy.viac.endpoints.Server;
import kopr.nikdy.viac.persistance.Database;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        startServer();
    }

    private static void startServer() throws ClassNotFoundException {
        try {
            Database.initialize();
            Server.registerEndpoints();

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        Runtime.getRuntime().addShutdownHook(new Thread(Database::close));
    }

}