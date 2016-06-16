package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Interface für die Statistiken eines laufenden Spieles
 */
public interface IGameStatistic {
    /**
     * Gibt die Anzahl beantworteter Fragen zurück
     * @return Anzahl Fragen
     */
    int getProgress();

    /**
     * Gibt die Anzahl korrekt beantworteten Fragen zurück
     * @return Anzahl korrekt
     */
    int getCorrect();

    /**
     * Gibt zurück, wie lange das Spiel schon läuft (noch nirgends implementiert)
     * @return Timestamp
     */
    long getPassedTime();
}
