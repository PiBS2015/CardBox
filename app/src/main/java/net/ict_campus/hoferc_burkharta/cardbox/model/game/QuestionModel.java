package net.ict_campus.hoferc_burkharta.cardbox.model.game;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

import java.util.Date;

/**
 * Diese Klasse modelliert eine Frage in einer Abfragerunde. Eine Frage besitzt immer eine Karte, die
 * abgefragt wird. Die Frage kann richtig oder falsch beantwortet werden.
 */
public class QuestionModel extends AbstractModel {

    private CardModel card;
    private SessionModel session;

    //Felder für die Statistik
    private boolean isCorrect = false;
    private long startTime;
    private long endTime;

    /**
     * Erzeugt eine neue Frage zu der Session
     * @param session die zugehörige Session
     * @param card die Karte
     */
    public QuestionModel(SessionModel session, CardModel card){
        this.card = card;
        this.session = session;
    }

    /**
     * Stellt die Frage, startet den Timer
     * @return die Frage
     */
    public QuestionModel ask(){
        startTime = new Date().getTime();
        return this;
    }

    /**
     * Antworte auf die Frage mit entweder wahr oder falsch
     * @param korrekt wurde die Frage korrekt beantwortet?
     */
    public void answer(boolean korrekt){
        this.isCorrect = korrekt;
        this.endTime = new Date().getTime();
    }

    public boolean isCorrect(){
        return isCorrect;
    }
    public CardModel getCard(){
        return card;
    }

    public long getPassedTime(){
        return endTime - startTime;
    }

    public SessionModel getOwningSession(){
        return session;
    }

}
