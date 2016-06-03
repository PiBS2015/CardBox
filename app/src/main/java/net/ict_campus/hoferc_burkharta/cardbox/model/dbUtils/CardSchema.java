package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

/**
 * Created by Burkharta on 03.06.2016.
 */
class CardSchema {
    static final String TABLE_NAME = "karte";

    static final String COL_ID = "ID_karte";
    static final String COL_DESCRIPTION = "description";
    static final String COL_FRONT_FACE = "front_ID";
    static final String COL_BACK_FACE = "back_ID";
    static final String COL_SET_KEY = "set_ID";

    static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_DESCRIPTION + " TEXT, " +
                    COL_FRONT_FACE + " INTEGER, " +
                    COL_BACK_FACE + " INTEGER, " +
                    COL_SET_KEY + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + COL_FRONT_FACE + ") " +
                    "REFERENCES " + FaceSchema.TABLE_NAME +
                    " (" + COL_ID + "), " +
                    "FOREIGN KEY(" + COL_BACK_FACE + ") " +
                    "REFERENCES " + FaceSchema.COL_ID +
                    " (" + COL_ID + "), " +
                    "FOREIGN KEY(" + COL_SET_KEY + ") " +
                    "REFERENCES " + SetSchema.TABLE_NAME +
                    " (" + SetSchema.COL_ID + ") "
                    + ")";
}
