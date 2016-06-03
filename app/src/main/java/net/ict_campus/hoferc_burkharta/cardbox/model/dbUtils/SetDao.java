package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.ISetModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burkharta on 03.06.2016.
 */
public class SetDao extends AbstractDao {

    public SetDao(Context context){
        super(context);
    }

    public void insertSet(SetModel set){
        SQLiteDatabase db = openDatabase(true);

        ContentValues setContent = new ContentValues();
        setContent.put(SetSchema.COL_NAME, set.getName());

        long id = db.insertOrThrow(SetSchema.TABLE_NAME,
                null,
                setContent);

        setIdOfObject(set, id);
    }

    public List<ISetModel> getAllSets(){
        List<ISetModel> sets = new ArrayList<>();

        SQLiteDatabase db = openDatabase(false);

        Cursor result = db.query(SetSchema.TABLE_NAME,
                new String[]{SetSchema.COL_ID, SetSchema.COL_NAME},
                null, null, null, null, null
                );

        if(result.moveToFirst()){
            do{
                SetModel set = new SetModel(result.getString(1));
                setIdOfObject(set, result.getLong(0));
                sets.add(set);
            } while(result.moveToNext());
        }

        return sets;
    }

    public void updateSet(SetModel set){
        //if()
    }

    public boolean deleteSet(SetModel set){
        return false;
    }
}
