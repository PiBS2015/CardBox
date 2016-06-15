package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

/**
 * Created by Burkharta on 03.06.2016.
 */
class FaceSchema {
    static final String TABLE_NAME = "seite";

    static final String COL_ID = "ID_seite";
    static final String COL_TEXT = "text";
    static final String COL_BILD = "bild";

    static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TEXT + " TEXT, " +
                    COL_BILD + " TEXT "
                    + ")";
}
