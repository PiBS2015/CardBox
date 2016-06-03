package net.ict_campus.hoferc_burkharta.cardbox.model;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

/**
 * Created by Burkharta on 01.06.2016.
 */
public class CardModel extends AbstractModel implements  ICardModel {

    private String description;
    private ICardSideModel[] faces;
    private String number;

    protected CardModel(){
        this.faces = new TextCardFace[2];
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public void setFrontFace(ICardSideModel face){
        this.faces[0] = face;
    }

    public void setBackFace(ICardSideModel face){
        this.faces[1] = face;
    }

    @Override
    public ICardSideModel getFace(CardSide which) {
        switch(which){
            case FRONT: return faces[0];
            case BACK: return faces[1];
        }
        throw new RuntimeException("Something went horribly wrong");
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
