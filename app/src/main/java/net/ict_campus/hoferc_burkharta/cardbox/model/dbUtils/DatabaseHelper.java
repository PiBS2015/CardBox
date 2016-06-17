package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Diese Klasse dient der Erzeugung der Datenbank und Bereitstellung der Daos.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    private static final String DATABASE_NAME = "card_box";
    private static final int DATABASE_VERSION = 3;


    /**
     * Liefert eine Instanz des DatabaseHelpers bei gegebenem Context zurück. Klassen ausserhalb des
     * Package können die Dao's benutzen, um Funktionen der Datenbank zu benützen
     * @param context
     * @return Instanz
     */
    static synchronized DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Liefert ein CardDao-Objekt. Dieses kann verwendet werden, um die Karten in der Datenbank zu
     * manipulieren.
     * @param context
     * @return CardDao
     */
    public static CardDao getCardDao(Context context){
        CardDao dao = new CardDao(context);
        return dao;
    }

    /**
     * Liefert ein SetDao-Objekt. Dieses kann verwendet werden, um die Sets in der Datenbank zu
     * manipulieren
     * @param context
     * @return SetDao
     */
    public static SetDao getSetDao(Context context){
        SetDao dao = new SetDao(context);
        return dao;
    }

    /**
     * Liefert ein SessionDao-Objekt. Dieses kann verwendet werden, um die Sessions in der Datenbank
     * zu manipulieren
     * @param context
     * @return SessionDao
     */
    public static SessionDao getSessionDao(Context context){
        SessionDao dao = new SessionDao(context);
        return dao;
    }

    /**
     * Liefert ein QuestionDao-Objekt. Dieses kann verwendet werden, um die Questions in der Datenbank zu
     * manipulieren
     * @param context
     * @return QuestionDao
     */
    public static QuestionDao getQuestionDao(Context context){
        QuestionDao dao = new QuestionDao(context);
        return dao;
    }


    /**
     * Erstellt die Datenbank
     * @param db die Datenbank
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SetSchema.CREATE_TABLE);
        db.execSQL(SessionSchema.CREATE_TABLE);
        db.execSQL(FaceSchema.CREATE_TABLE);
        db.execSQL(CardSchema.CREATE_TABLE);
        db.execSQL(QuestionSchema.CREATE_TABLE);
    }

    /**
     * Sorgt dafür, dass die Datenbank auf die neuste Version upgedated wird. Erstellt einfach alle
     * Tabellen, die in der alten Version noch nicht vorhanden waren
     * @param db die Datenbank
     * @param oldVersion die alte Version der Datenbank
     * @param newVersion die aktuelle Version der Datenbank
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
