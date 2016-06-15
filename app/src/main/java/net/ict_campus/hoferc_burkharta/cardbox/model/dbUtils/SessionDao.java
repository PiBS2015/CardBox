package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionModel;

/**
 * Created by Burkharta on 03.06.2016.
 */
public class SessionDao extends AbstractDao{

    public SessionDao(Context context){
        super(context);
    }

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
