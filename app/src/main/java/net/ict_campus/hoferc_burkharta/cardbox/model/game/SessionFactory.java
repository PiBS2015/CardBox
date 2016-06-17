package net.ict_campus.hoferc_burkharta.cardbox.model.game;

import android.content.Context;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.List;

/**
 * Diese Klasse baut eine Session auf, wobei die Reihenfolge der Karten im Set festgelegt wird.
 * Im Moment ist jedoch nur der FIXED Modus implementiert
 */
public class SessionFactory {

    private Context context;

    /**
     * Bei FIXED werden die Karten in der Reihenfolge abgefragt, in der sie aus der Datenbank kommen.
     * Bei RANDOM wäre die Idee, dass die Karten gemischt werden, dies wurde aber noch nicht
     * implementiert.
     */
    public enum Mode{
        RANDOM, FIXED
    }

    /**
     * Erzeugt eine neue Sessionfactory in einem Kontext
     * @param context der Kontext
     */
    public SessionFactory(Context context){
        this.context = context;
    }

    /**
     * Liefert eine Session, die gespielt werden kann.
     * @param mode welcher modus
     * @param set welches Set
     * @return Session
     */
    public SessionModel createSession(Mode mode, SetModel set){
        switch(mode){
            case FIXED:
                List<CardModel> cards = set.getCards();
                return new SessionModel(cards, context);
            default:
                // Not yet implemented
                return null;
        }
    }
}
