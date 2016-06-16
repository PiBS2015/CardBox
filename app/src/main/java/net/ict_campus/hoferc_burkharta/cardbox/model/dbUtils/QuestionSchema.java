package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

/**
 * Diese Klasse dient der Speicherung des Datenbankschemas der Question-tabelle
 */
class QuestionSchema {

    static final String TABLE_NAME = "bearbeitung";

    static final String COL_ID = "ID_bearbeitung";
    static final String COL_RICHTIG = "antwortKorrekt";
    static final String COL_DAUER = "dauer";
    static final String COL_KARTE = "karte_ID";
    static final String COL_SESSION = "session_ID";

    static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_RICHTIG + " BOOLEAN, " +
                    COL_DAUER + " TIME, " +
                    COL_KARTE + " INTEGER NOT NULL, " +
                    COL_SESSION + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + COL_KARTE + ") " +
                    "REFERENCES " + CardSchema.TABLE_NAME +
                    " (" + CardSchema.COL_ID + "), " +
                    "FOREIGN KEY(" + COL_SESSION + ") " +
                    "REFERENCES " + SessionSchema.TABLE_NAME +
                    " (" + SessionSchema.COL_ID + ") "
                    + ")";
}
