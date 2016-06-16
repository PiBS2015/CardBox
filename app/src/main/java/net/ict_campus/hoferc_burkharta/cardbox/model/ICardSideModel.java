package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Interface für die Kartenseiten. Die Seite einer Karte hat einen Typ (CardFaceType) und Ressourcen.
 */
public interface ICardSideModel {
    /**
     * Gibt den Typ der Karte zurück
     * @return CardFaceType typ
     */
    CardFaceType getType();

    /**
     * Gibt die Ressourcen der Karte zurück.
     * @return resources:
     * resources[0] - Text
     * resources[1] - Bild
     */
    String[] getRessource();
}
