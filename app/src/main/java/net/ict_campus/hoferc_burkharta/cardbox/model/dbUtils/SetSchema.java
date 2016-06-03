package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

/**
 * Created by Burkharta on 03.06.2016.
 */
class SetSchema {
    static final String TABLE_NAME = "'set'";

    static final String COL_NAME = "name";
    static final String COL_ID = "ID_set";

    static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    " ( '" +
                    COL_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                    COL_NAME + "' TEXT NOT NULL"
                    + ")";
}
