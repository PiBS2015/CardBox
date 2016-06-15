package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Burkharta on 02.06.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    private static final String DATABASE_NAME = "card_box";
    private static final int DATABASE_VERSION = 3;

//    private CardDao cardDao;
//    private SetDao setDao;
//    private SessionDao sessionDao;
//    private QuestionDao questionDao;


    static synchronized DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static CardDao getCardDao(Context context){
        CardDao dao = new CardDao(context);
        return dao;
    }

    public static SetDao getSetDao(Context context){
        SetDao dao = new SetDao(context);
        return dao;
    }

    public static SessionDao getSessionDao(Context context){
        SessionDao dao = new SessionDao(context);
        return dao;
    }

    public static QuestionDao getQuestionDao(Context context){
        QuestionDao dao = new QuestionDao(context);
        return dao;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SetSchema.CREATE_TABLE);
        db.execSQL(SessionSchema.CREATE_TABLE);
        db.execSQL(FaceSchema.CREATE_TABLE);
        db.execSQL(CardSchema.CREATE_TABLE);
        db.execSQL(QuestionSchema.CREATE_TABLE);
        Log.v(this.getClass().getSimpleName(), "database created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(getClass().getSimpleName(), "Upgrade! " + oldVersion + ", " + newVersion);
        onCreate(db);
    }
}
