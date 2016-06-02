package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Created by Burkharta on 01.06.2016.
 */
class PictureCardFace implements ICardSideModel{
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
