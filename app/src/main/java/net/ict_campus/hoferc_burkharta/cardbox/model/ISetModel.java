package net.ict_campus.hoferc_burkharta.cardbox.model;

import java.util.List;

/**
 * Interface eines Sets. Ein Set besteht aus mehreren Karteikarten und hat einen Namen.
 * Es bildet die Einheit, die in einer Session abgefragt wird.
 *
 */
public interface ISetModel {
    /**
     * Der Anzeigename des Sets
     * @return String Name
     */
    String getName();

    /**
     * Gibt eine Liste mit allen Karten zurück, die in dem Set sind
     * @return Liste von Karten
     */
    List<CardModel> getCards();
    //Soll & kann-Ziele

    /**
     * Noch nicht implementiert
     * @return
     */
    String getStyleRessource();

    /**
     * Noch nicht implementiert
     * @return
     */
    String getPictureRessource();
}
