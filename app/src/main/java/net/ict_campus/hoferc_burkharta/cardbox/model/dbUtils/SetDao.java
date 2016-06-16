package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Das Database Access Object eines Sets. Diese Klasse bietet Diese Klasse bietet grundlegende
 * CRUD-Funktionalität
 */
public class SetDao extends AbstractDao {

    private Context context;

    /**
     * Erzeugt ein neues SessionDao mit gegebenem Kontext. Wird vom DatabaseHelper aufgerufen
     * @param context der App-Context
     */
    public SetDao(Context context){
        super(context);
    }

    /**
     * speichert ein SetModel in der Datenbank. Ist das Set bereits in der Datenbank vorhanden, so
     * werden dessen Inhalte neu geschrieben.
     * @param set das zu speichernde SetModel
     */
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

    /**
     * Die Karten werden aus der Datenbank in das Set geladen
     * @param set das set, das gefüllt werden soll
     */
    public void fillSet(SetModel set){
        CardDao dao = DatabaseHelper.getCardDao(context);
        List<CardModel> cards = dao.getAllCards(set);
        set.setCards(cards);
    }

    /**
     * Gibt eine Liste mit allen Sets in der Datenbank zurück. Diese sind noch ungefüllt, es sind
     * also noch keine Karten vorhanden
     * @return Liste aller Sets
     */
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

    /**
     * Löscht das Set aus der Datenbank. Beachte, dass zuerst alle zugehörigen Karten gelöscht
     * werden müssen.
     * @param set das zu löschende Set
     * @return true, falls die Löschung erfolgreich war, false sonst.
     */
    public boolean deleteSet(SetModel set){
        AbstractModel dbLayerSet = (AbstractModel) set;
        if(dbLayerSet.isInDatabase()){
            SQLiteDatabase db = openDatabase(true);
            db.delete(SetSchema.TABLE_NAME, SetSchema.COL_ID + " = ?", new String[]{dbLayerSet.getId() + ""});
            return true;
        }
        return false;
    }
}
