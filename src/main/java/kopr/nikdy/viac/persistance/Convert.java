package kopr.nikdy.viac.persistance;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Convert {

    public static final int BITS_IN_BYTE = 8;

    public static byte[] toBytes(UUID uuid) throws SQLException {
        byte[] uuidBytes = new byte[16];

        long leastSignificantBits = uuid.getLeastSignificantBits();
        for (int i = 0; i < Long.BYTES; i++) {
            uuidBytes[i] = (byte) leastSignificantBits;
            leastSignificantBits >>= BITS_IN_BYTE;
        }

        long mostSignificantBits = uuid.getMostSignificantBits();
        for (int i = Long.BYTES; i < Long.BYTES * 2; i++) {
            uuidBytes[i] = (byte) mostSignificantBits;
            mostSignificantBits >>= BITS_IN_BYTE;
        }

        return uuidBytes;
    }

    public static UUID fromBytes(byte[] bytes) {
        long mostSignificantBits = 0;
        for (int i = 2 * Long.BYTES - 1; i >= Long.BYTES; i--) {
            mostSignificantBits <<= BITS_IN_BYTE;
            mostSignificantBits |= bytes[i];
        }

        long leastSignificantBits = 0;
        for (int i = Long.BYTES - 1; i >= 0; i--) {
            leastSignificantBits <<= BITS_IN_BYTE;
            leastSignificantBits |= bytes[i];
        }

        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    public static Timestamp toTimestamp(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.from(timestamp.toInstant());
    }

}
