package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Created by Burkharta on 27.05.2016.
 */
public enum CardSide {
    FRONT, BACK;

    public CardSide opposite(){
        switch(this){
            case FRONT:
                return BACK;
            case BACK:
                return FRONT;
            default:
                return null;
        }
    }
}
