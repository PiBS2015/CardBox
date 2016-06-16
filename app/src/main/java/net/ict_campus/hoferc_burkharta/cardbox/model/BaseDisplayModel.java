package net.ict_campus.hoferc_burkharta.cardbox.model;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

/**
 * Diese Klasse bildet die Grundklasse für ein Element, das auf dem Bildschirm in einer Liste
 * dargestellt werden kann. Weitere Funktionen für Bilder, etc. können noch hinzugefügt werden.
 */
public abstract class BaseDisplayModel extends AbstractModel {

    /**
     * Anzeigetext
     * @return String text
     */
    public abstract String getName();
}
