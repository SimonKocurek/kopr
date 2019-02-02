package kopr.nikdy.viac.persistance;

import java.sql.SQLException;

public class DatabaseNotInitializedException extends SQLException {

    public DatabaseNotInitializedException() {
    }

    public DatabaseNotInitializedException(Throwable cause) {
        super(cause);
    }

}
