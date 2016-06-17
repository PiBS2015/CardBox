package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Diese Klasse dient dazu, eine neue Karte zu erstellen oder eine bestehende Karte zu verändern.
 * erzeugte Karten sind nicht persistent.
 */
public class CardBuilder {
    CardModel card;
    String frontText;
    String backText;
    String frontPictureRessource;
    String backPictureRessource;

    /**
     * Erzeugt einen neuen Cardbuilder mit gegebenem Set. Diesem wird die neue Karte zugewiesen.
     * @param set das Set, das die neue Karte erhalten soll
     */
    public CardBuilder(SetModel set){
        this.card = new CardModel(set);
    }

    /**
     * Erzeugt einen neuen CardBuilder mit gegebener Karte. Diese kann so bearbeitet werden.
     * @param card die zu verändernde Karte
     */
    public CardBuilder(CardModel card){
        this.card = card;

        // Lade die Inhalte der Karte
        extractRessources(CardSide.FRONT);
        extractRessources(CardSide.BACK);
    }

    /**
     * holt die Informationen aus den Faces der Karte heraus
     * @param whichSide welche Seite der Karte
     */
    private void extractRessources(CardSide whichSide){
        String[] ressources = card.getFace(whichSide).getRessource();
        switch(whichSide){
            case FRONT:
                frontText = ressources[0];
                if(ressources.length >= 2) frontPictureRessource = ressources[1];
                break;
            case BACK:
                backText = ressources[0];
                if(ressources.length >= 2) backPictureRessource = ressources[1];
                break;
        }
    }

    /**
     * Setze die Beschreibung der Karte (noch keine Verwendung)
     * @param description neue Beschreibung
     * @return die Karte
     */
    public CardBuilder setDescription(String description){
        this.card.setDescription(description);
        return this;
    }

    /**
     * Setze die Nummer der Karte (noch keine Verwendung)
     * @param number Nummer
     * @return die Karte
     */
    public CardBuilder setNumber(String number){
        this.card.setNumber(number);
        return this;
    }

    /**
     * Setze den Text auf der spezifizierten Kartenseite
     * @param whichSide welche Seite
     * @param text der Text
     * @return die Karte
     */
    public CardBuilder setFaceText(CardSide whichSide, String text){

        switch(whichSide){
            case FRONT: frontText = text;
                break;
            case BACK: backText = text;
                break;
        }
        return this;
    }

    /**
     * Setze eine URI eines Bildes für die spezifizierte Kartenseite (noch keine Verwendung)
     * @param whichSide welche Seite
     * @param ressource die URI des Bildes
     * @return die Karte
     */
    public CardBuilder setFacePicture(CardSide whichSide, String ressource){
        switch (whichSide){
            case FRONT: frontPictureRessource = ressource;
                break;
            case BACK: backPictureRessource = ressource;
                break;
        }
        return this;
    }

    /**
     * Baut die Karte zusammen und liefert sie zurück
     * @return die fertige Karte
     */
    public CardModel build(){
        ICardSideModel[] faces = generateFaces();
        card.setFrontFace(faces[0]);
        card.setBackFace(faces[1]);

        return card;
    }

    /**
     * Baut neue Faces beim Bau der Karte
     * @return die Seiten der Karte
     */
    private ICardSideModel[] generateFaces(){
        ICardSideModel[] faces = new ICardSideModel[2];
        if(isPictureFace(CardSide.FRONT)){
            faces[0] = new PictureCardFace(frontPictureRessource, frontText);
        } else{
            faces[0] = new TextCardFace(frontText);
        }
        if(isPictureFace(CardSide.BACK)){
            faces[1] = new PictureCardFace(backPictureRessource, backText);
        } else{
            faces[1] = new TextCardFace(backText);
        }
        return faces;
    }

    /**
     * Gibt an, ob die Seite ein Bild beeinhaltet (Gibt im Moment immer false zurück)
     * @param side welche Kartenseite
     * @return false
     */
    private boolean isPictureFace(CardSide side){
        return false;
        /*
        switch(side){
            case FRONT:
                return this.frontPictureRessource != null;
            case BACK:
                return this.backPictureRessource != null;
        }
        return false;
        */
    }

}
