package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Created by Burkharta on 01.06.2016.
 */
class TextCardFace implements ICardSideModel{
    private static final CardFaceType type = CardFaceType.TEXT;
    private String text;

    public TextCardFace(String ressource){
        this.text = ressource;
    }

    @Override
    public CardFaceType getType() {
        return this.type;
    }

    @Override
    public String[] getRessource() {
        if(text == null){
            throw new RuntimeException("Keine Ressource für die Kartenseite " + this + " vorhanden!");
        }
        return new String[]{this.text};
    }

    public void setRessource(String ressource) {
        this.text = ressource;
    }
}
