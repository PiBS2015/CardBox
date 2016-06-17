package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import java.io.Serializable;

/**
 * Diese Klasse beschreibt die Datenbankanbindung der Models. Hier werden relevante Daten
 * bezüglich der Verwaltung der Datenbankobjekten gespeichert und verwaltet (die ID).
 */
public abstract class AbstractModel implements Serializable {
    long id = -1;

    protected AbstractModel(){    }

    void setId(long id){
        this.id = id;
    }

    long getId(){
        return id;
    }

    public boolean isInDatabase(){
        return id != -1;
    }
}
