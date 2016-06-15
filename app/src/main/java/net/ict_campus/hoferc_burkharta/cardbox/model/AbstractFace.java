package net.ict_campus.hoferc_burkharta.cardbox.model;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

/**
 * Created by Burkharta on 03.06.2016.
 */
public abstract class AbstractFace extends AbstractModel implements ICardSideModel{
    private String cardText;

    protected void setText(String text){
        this.cardText = text;
    }

    protected String getText(){
        return cardText;
    }
}
