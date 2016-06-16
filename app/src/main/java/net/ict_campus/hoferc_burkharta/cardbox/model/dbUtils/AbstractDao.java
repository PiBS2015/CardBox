package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Basisklasse für ein Dao. Hier wird der Context der Applikation gehandelt, wie auch das Öffnen
 * der Datenbankverbindung. Ausserdem bietet sie Methoden, um die IDs der Models zu verwalten.
 */
public abstract class AbstractDao {
    private final Context CONTEXT;

    protected AbstractDao(Context context){
        this.CONTEXT = context;
    }

    /**
     * Gibt die (Datenbank-) ID des Models zurück
     * @param dbObj Das Model
     * @return long ID
     */
    protected long getIdOfObject(AbstractModel dbObj){
        return dbObj.getId();
    }

    /**
     * Setzt die ID des Models neu. Diese soll nur von der Datenbank gesetzt werden, ansonsten
     * kommt es zu Konflikten. Models, die bereits eine ID haben, gelten bereits als in der Daten-
     * bank verändert und sollen ihre ID nicht mehr verändern
     * @param dbObj Das Model
     * @param id Die neue ID des Models
     */
    protected void setIdOfObject(AbstractModel dbObj, long id){
        dbObj.setId(id);
    }

    /**
     * Gibt eine Datenbankverbindung zurück, muss wieder geschlossen werden
     * @param writable ob die Datenbank beschreibbar geöffnet werden soll
     * @return Datenbankverbindung
     */
    protected SQLiteDatabase openDatabase(boolean writable){
        if(writable){
            return DatabaseHelper.getInstance(CONTEXT).getWritableDatabase();
        }
        else{
            return DatabaseHelper.getInstance(CONTEXT).getReadableDatabase();
        }
    }

    protected Context getContext(){
        return this.CONTEXT;
    }
}
