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

    private Context context;

    public SetDao(Context context){
        super(context);
    }

    public void updateOrInsertSet(SetModel set){
        if(set.isInDatabase()){
            insertSetContent(openDatabase(true), set);
        } else {
            insertSet(set);
        }
    }

    private void insertSet(SetModel set){
        SQLiteDatabase db = openDatabase(true);

        long id = insertSetContent(db, set);

        setIdOfObject(set, id);
        db.close();

    }

    private long insertSetContent(SQLiteDatabase db, SetModel set){
        ContentValues setContent = new ContentValues();
        setContent.put(SetSchema.COL_NAME, set.getName());

        return db.insertOrThrow(SetSchema.TABLE_NAME,
                null,
                setContent);
    }

    public void fillSet(SetModel set){
        CardDao dao = DatabaseHelper.getCardDao(context);
        List<CardModel> cards = dao.getAllCards(set);
        set.setCards(cards);
    }

    public List<SetModel> getAllSets(){
        List<SetModel> sets = new ArrayList<>();

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

        result.close();

        return sets;
    }

    public boolean deleteSet(ISetModel set){
        AbstractModel dbLayerSet = (AbstractModel) set;
        if(dbLayerSet.isInDatabase()){
            SQLiteDatabase db = openDatabase(true);
            db.delete(SetSchema.TABLE_NAME, SetSchema.COL_ID + " = ?", new String[]{dbLayerSet.getId() + ""});
            return true;
        }
        return false;
    }
}
