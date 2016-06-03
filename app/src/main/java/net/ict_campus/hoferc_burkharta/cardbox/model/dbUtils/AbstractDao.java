package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Burkharta on 03.06.2016.
 */
public abstract class AbstractDao {
    private final Context CONTEXT;

    protected AbstractDao(Context context){
        this.CONTEXT = context;
    }

    protected long getIdOfObject(AbstractModel dbObj){
        return dbObj.getId();
    }

    protected void setIdOfObject(AbstractModel dbObj, long id){
        dbObj.setId(id);
    }

    protected SQLiteDatabase openDatabase(boolean writable){
        if(writable){
            return DatabaseHelper.getInstance(CONTEXT).getWritableDatabase();
        }
        else{
            return DatabaseHelper.getInstance(CONTEXT).getReadableDatabase();
        }
    }
}
