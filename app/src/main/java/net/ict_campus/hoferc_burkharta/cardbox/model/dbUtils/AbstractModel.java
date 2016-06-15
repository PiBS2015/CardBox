package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractDao;

import java.io.Serializable;

/**
 * Created by Burkharta on 03.06.2016.
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