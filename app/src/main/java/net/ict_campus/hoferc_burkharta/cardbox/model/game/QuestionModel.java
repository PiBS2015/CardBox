package net.ict_campus.hoferc_burkharta.cardbox.model.game;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;

import java.util.Date;

/**
 * Created by Burkharta on 08.06.2016.
 */
public class QuestionModel extends AbstractModel {
    private boolean isCorrect = false;
    private long startTime;
    private long endTime;
    private CardModel card;
    private SessionModel session;

    public QuestionModel(SessionModel session, CardModel card){
        this.card = card;
        this.session = session;
    }

    public QuestionModel ask(){
        startTime = new Date().getTime();
        return this;
    }

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
