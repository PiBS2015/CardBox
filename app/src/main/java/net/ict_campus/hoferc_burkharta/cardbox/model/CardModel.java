package net.ict_campus.hoferc_burkharta.cardbox.model;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

/**
 * Created by Burkharta on 01.06.2016.
 */
public class CardModel extends BaseDisplayModel {

    private String description;
    private ICardSideModel[] faces;
    private String number;
    private SetModel owningSet;

    protected CardModel(SetModel owner){
        // Other kind not yet implemented
        this.faces = new TextCardFace[2];
        this.owningSet = owner;
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

    public void setOwner(SetModel set){
        this.owningSet = set;
    }

    public SetModel getOwner(){
        return owningSet;
    }

    public ICardSideModel getFace(CardSide which) {
        switch(which){
            case FRONT: return faces[0];
            case BACK: return faces[1];
        }
        throw new RuntimeException("Something went horribly wrong");
    }

    public String getDescription() {
        return description;
    }

    public String getNumber() {
        return number;
    }

    public String getName(){
        //get the Text on the Front Side of the Card
        return faces[0].getRessource()[0];
    }
}
