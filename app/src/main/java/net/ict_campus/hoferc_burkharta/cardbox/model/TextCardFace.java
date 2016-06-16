package net.ict_campus.hoferc_burkharta.cardbox.model;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

/**
 * Diese Klasse beschreibt ein Face einer Karte mit nur Text.
 */
class TextCardFace extends AbstractModel implements ICardSideModel{
    private static final CardFaceType type = CardFaceType.TEXT;
    private String text;

    /**
     * Erstellt ein Face mit gegebenem Text
     * @param ressource Text
     */
    public TextCardFace(String ressource){
        this.text = ressource;
    }

    @Override
    public CardFaceType getType() {
        return this.type;
    }

    @Override
    /**
     * @see ICardSideModel
     */
    public String[] getRessource() {
        if(text == null){
            throw new RuntimeException("Keine Ressource für die Kartenseite " + this + " vorhanden!");
        }
        return new String[]{this.text, null};
    }

    /**
     * Setzt den text neu
     * @param ressource
     */
    public void setRessource(String ressource) {
        this.text = ressource;
    }
}
