package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * enum für die verschiedenen Kartenseiten
 */
public enum CardSide {
    FRONT, BACK;

    /**
     * Gibt die gegenüberliegende Seite zurück
     * @return opposite
     */
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
