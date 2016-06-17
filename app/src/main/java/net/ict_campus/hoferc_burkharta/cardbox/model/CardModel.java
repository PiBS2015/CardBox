package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Diese Klasse modelliert eine Karteikarte. Eine Karte hat zwei verschiedene Seiten, die das
 * Interface ICardSideModel erfüllen. Um eine Karte zu erstellen oder verändern steht die Klasse
 * CardBuilder zur Verfügung. Eine Karteikarte gehört immer einem Set.
 */
public class CardModel extends BaseDisplayModel {

    private String description;
    private ICardSideModel[] faces;
    private String number;
    private SetModel owningSet;

    /**
     * Erstellt eine neue, leere Karte, die zum Set owner gehört
     * @param owner das Set, in dem sich die Karte befinden soll
     */
    protected CardModel(SetModel owner){
        // Other kind not yet implemented
        this.faces = new TextCardFace[2];
        this.owningSet = owner;
    }

    /**
     * Setze die Beschreibung der Karte (noch keine Verwendung)
     * @param description die Beschreibung
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Setze die Nummer der Karte (noch keine Verwendung)
     * @param number die Nummer
     */
    public void setNumber(String number){
        this.number = number;
    }

    /**
     * Setze das FrontFace neu
     * @param face die Seite
     */
    public void setFrontFace(ICardSideModel face){
        this.faces[0] = face;
    }

    /**
     * Setze das BackFace neu
     * @param face die Seite
     */
    public void setBackFace(ICardSideModel face){
        this.faces[1] = face;
    }

    /**
     * Verschiebe die Karte in ein anderes Set
     * @param set das neue Set
     */
    public void setOwner(SetModel set){
        this.owningSet = set;
    }

    /**
     * Gibt das besitzende Set zurück
     * @return der Besitzer
     */
    public SetModel getOwner(){
        return owningSet;
    }

    /**
     * Liefert die verlangte Seite der Karte
     * @param which welche Seite
     * @return die Seite
     */
    public ICardSideModel getFace(CardSide which) {
        switch(which){
            case FRONT: return faces[0];
            case BACK: return faces[1];
        }
        throw new RuntimeException("Something went horribly wrong");
    }

    /**
     * Liefert die Beschreibung der Karte zurück
     * @return die Beschreibung
     */
    public String getDescription() {
        return description;
    }

    /**
     * Liefert die Nummer der Karte zurück
     * @return die Nummer
     */
    public String getNumber() {
        return number;
    }

    /**
     * Gibt den Anzeigename der Karte zurück. Dieser ist lediglich der Text auf der Vorderseite der
     * Karte
     * @return Anzeigetext
     */
    public String getName(){
        //get the Text on the Front Side of the Card
        return faces[0].getRessource()[0];
    }
}
