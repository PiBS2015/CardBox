package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

/**
 * Created by Burkharta on 03.06.2016.
 */
class SessionSchema {

    static final String TABLE_NAME = "session";

    static final String COL_ID = "ID_session";
    static final String COL_DATUM = "datum";

    static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_DATUM + " DATETIME NOT NULL"
                    + ")";
}