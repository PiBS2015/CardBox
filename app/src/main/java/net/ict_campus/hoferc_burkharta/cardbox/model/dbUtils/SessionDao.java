package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionModel;

/**
 * Das Database Access Object einer Session. Diese Klasse bietet nur die Funktion, eine Session
 * einzufügen. Das Lesen der Einträge muss später noch implementiert werden.
 */
public class SessionDao extends AbstractDao{

    /**
     * Erstellt ein neues SessionDao-Objekt mit gegebenem Context. Wird vom Databasehelper aufgerufen
     * @param context
     */
    SessionDao(Context context){
        super(context);
    }

    /**
     * Speichert eine Session in der Datenbank.
     * @param session die session
     */
    public void insertSession(SessionModel session){
        if(session.isInDatabase()){
            //nothing to do
        } else {
            SQLiteDatabase db = openDatabase(true);
            ContentValues contVals = new ContentValues();
            contVals.put(SessionSchema.COL_DATUM, session.getDate());
            long id = db.insert(SessionSchema.TABLE_NAME, null,contVals);
            setIdOfObject(session, id);
        }
    }
}
