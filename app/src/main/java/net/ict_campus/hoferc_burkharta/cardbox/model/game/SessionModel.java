package net.ict_campus.hoferc_burkharta.cardbox.model.game;

import android.content.Context;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.IGameStatistic;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.QuestionDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.SessionDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.QuestionModel;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Diese Klasse beschreibt eine Session, beziehungsweise eine Abfragerunde. Zur Erzeugung einer
 * Session kann die SessionFactory verwendet werden. Über die Methoden hasNext und nextQuestion
 * kann das Spiel fortgeführt werden. Das Spiel ist fertig, wenn jede Frage beantwortet wurde.
 * Die Session wird auch mit allen gestellten Fragen persistent gespeichert.
 */
public class SessionModel extends AbstractModel implements IGameStatistic, Serializable {
    //Daos zur persistenten Speicherung
    private SessionDao sDao;
    private QuestionDao qDao;

    private long startTime;
    private long endTime;
    private boolean isFinished;

    //Zeiger auf die momentane Frage
    private int current;
    //Die Liste der Fragen
    private List<QuestionModel> questions;
    private QuestionModel activeQuestion;

    /**
     * Erzeugt eine neue Session mit einer gegebenen Liste von Karten. Diese werden genau in dieser
     * Reihenfolge abgefragt.
     * @param cards die Liste der Karten
     * @param context der Kontext
     */
    SessionModel(List<CardModel> cards, Context context){
        questions = new LinkedList<>();
        for(CardModel card : cards){
            questions.add(new QuestionModel(this, card));
        }
        current = 0;
        this.sDao = DatabaseHelper.getSessionDao(context.getApplicationContext());
        this.qDao = DatabaseHelper.getQuestionDao(context.getApplicationContext());

    }

    /**
     * Starte die Session, die Zeit wird gestoppt und die Session persistiert
     */
    public void start(){
        startTime = new Date().getTime();
        sDao.insertSession(this);
    }

    /**
     * Gibt die nächste Frage zurück. Zuerst sollte mit hasNext() geprüft werden, ob es eine
     * nächste Frage gibt.
     * @return nächste Frage
     */
    public QuestionModel nextQuestion(){
        QuestionModel currentQuestion = questions.get(current);
        current++;
        activeQuestion = currentQuestion;
        return currentQuestion;
    }

    /**
     * Gibt zurück, ob die Session fertig ist.
     * @return true, falls noch Fragen übrig sind, false sonst
     */
    public boolean hasNext(){
        return current < questions.size();
    }

    /**
     * Beendet das Spiel
     */
    public void finish(){
        isFinished = true;
        endTime = new Date().getTime();
    }

    /**
     * Die aktive Frage wird beantwortet
     * @param isCorrect wurde sie richtig beantwortet
     */
    public void answerActiveQuestion(boolean isCorrect){
        activeQuestion.answer(isCorrect);
        qDao.insertQuestion(activeQuestion);
    }

    public long getDate(){
        return startTime;
    }


    @Override
    public int getProgress() {
        return questions.size() - current;
    }

    @Override
    public int getCorrect() {
        int counter = 0;
        for(QuestionModel q : questions){
            if(q.isCorrect()) counter++;
        }
        return counter;
    }

    @Override
    public long getPassedTime() {
        if(isFinished){
            return endTime - startTime;
        } else {
            long now = new Date().getTime();
            return now - startTime;
        }
    }
}
