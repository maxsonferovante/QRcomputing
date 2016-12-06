package database;

import android.provider.BaseColumns;

/**
 * Created by mferovante on 06/12/16.
 */

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ENROLLMENT = "enrollment";
        public static final String COLUMN_EMAIL = "email";
    }
}
