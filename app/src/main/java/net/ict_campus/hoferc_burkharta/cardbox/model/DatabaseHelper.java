package net.ict_campus.hoferc_burkharta.cardbox.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Burkharta on 02.06.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    private static final String DATABASE_NAME = "card_box";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SET = "set";
    private static final String TABLE_KARTE = "karte";
    private static final String TABLE_SEITE = "seite";
    private static final String TABLE_BEARBEITUNG = "bearbeitung";
    private static final String TABLE_SESSION = "session";

    private static final String COL_SET_NAME = "name";
    private static final String COL_SET_ID = "ID_set";

    private static final String COL_KARTE_ID = "ID_karte";
    private static final String COL_KARTE_DESCRIPTION = "description";
    private static final String COL_KARTE_FRONT_FACE = "front_ID";
    private static final String COL_KARTE_BACK_FACE = "back_ID";
    private static final String COL_KARTE_SET_KEY = "set_ID";

    private static final String COL_SEITE_ID = "ID_seite";
    private static final String COL_SEITE_TEXT = "text";
    private static final String COL_SEITE_BILD = "bild";

    private static final String COL_BEARBEITUNG_ID = "ID_bearbeitung";
    private static final String COL_BEARBEITUNG_RICHTIG = "antwortKorrekt";
    private static final String COL_BEARBEITUNG_DAUER = "dauer";
    private static final String COL_BEARBEITUNG_KARTE = "karte_ID";
    private static final String COL_BEARBEITUNG_SESSION = "session_ID";

    private static final String COL_SESSION_ID = "ID_session";
    private static final String COL_SESSION_DATUM = "datum";

    private static final String CREATE_TABLE_SET =
            "CREATE TABLE '" + TABLE_SET +
                    "' ( '" +
                    COL_SET_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                    COL_SET_NAME + "' TEXT NOT NULL"
                    + ")";

    private static final String CREATE_TABLE_KARTE =
            "CREATE TABLE " + TABLE_KARTE +
                    "(" +
                    COL_KARTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_KARTE_DESCRIPTION + " TEXT, " +
                    COL_KARTE_FRONT_FACE + " INTEGER, " +
                    COL_KARTE_BACK_FACE + " INTEGER, " +
                    COL_KARTE_SET_KEY + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + COL_KARTE_FRONT_FACE + ") " +
                    "REFERENCES " + TABLE_SEITE +
                        " (" + COL_SEITE_ID + "), " +
                    "FOREIGN KEY(" + COL_KARTE_BACK_FACE + ") " +
                    "REFERENCES " + TABLE_SEITE +
                        " (" + COL_SEITE_ID + "), " +
                    "FOREIGN KEY(" + COL_KARTE_SET_KEY + ") " +
                    "REFERENCES " + TABLE_SET +
                        " (" + COL_SET_ID + ") "
                    + ")";

    private static  final String CREATE_TABLE_SEITE =
            "CREATE TABLE " + TABLE_SEITE +
                    "(" +
                    COL_SEITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_SEITE_TEXT + " TEXT, " +
                    COL_SEITE_BILD + " TEXT "
                    + ")";

    private static final String CREATE_TABLE_BEARBEITUNG =
            "CREATE TABLE " + TABLE_BEARBEITUNG +
                    "(" +
                    COL_BEARBEITUNG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_BEARBEITUNG_RICHTIG + " BOOLEAN, " +
                    COL_BEARBEITUNG_DAUER + " TIME, " +
                    COL_BEARBEITUNG_KARTE + " INTEGER NOT NULL, " +
                    COL_BEARBEITUNG_SESSION + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + COL_BEARBEITUNG_KARTE + ") " +
                    "REFERENCES " + TABLE_KARTE +
                    " (" + COL_KARTE_ID + "), " +
                    "FOREIGN KEY(" + COL_BEARBEITUNG_SESSION + ") " +
                    "REFERENCES " + TABLE_SESSION +
                    " (" + COL_SESSION_ID + ") "
                    + ")";

    private static final String CREATE_TABLE_SESSION =
            "CREATE TABLE " + TABLE_SESSION +
                    "(" +
                    COL_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_SESSION_DATUM + " DATETIME NOT NULL"
                    + ")";


    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addCard(ICardModel cardToAdd){
        ICardSideModel front = cardToAdd.getFace(CardSide.FRONT);
        ICardSideModel back = cardToAdd.getFace(CardSide.BACK);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SET);
        db.execSQL(CREATE_TABLE_SESSION);
        db.execSQL(CREATE_TABLE_SEITE);
        db.execSQL(CREATE_TABLE_KARTE);
        db.execSQL(CREATE_TABLE_BEARBEITUNG);
        Log.v(this.getClass().getSimpleName(), "database created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
