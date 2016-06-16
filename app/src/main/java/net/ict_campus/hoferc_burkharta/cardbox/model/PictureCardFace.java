package net.ict_campus.hoferc_burkharta.cardbox.model;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

/**
 * Diese Klasse beschreibt ein Face einer Karte mit einem Bild und einem Text.
 * --WiP--
 */
class PictureCardFace extends AbstractModel implements ICardSideModel{
    private static final CardFaceType type = CardFaceType.PICTURE;
    private String picture;
    private String text;

    PictureCardFace(String picture, String subTitle){
        this.picture = picture;
        this.text = subTitle;
    }

    @Override
    public CardFaceType getType() {
        return type;
    }

    @Override
    public String[] getRessource() {
        if(picture == null){
            throw new RuntimeException("Keine Ressource für die Kartenseite " + this + " vorhanden!");
        }
        return new String[]{picture, text};
    }

    public void setRessource(String ressource) {
        loadPicture(ressource);
    }

    private void loadPicture(String ressource){
        this.picture = ressource;
    }
}
