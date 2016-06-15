package net.ict_campus.hoferc_burkharta.cardbox.model.game;

import android.content.Context;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.IGameStatistic;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.QuestionDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.SessionDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.QuestionModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Burkharta on 08.06.2016.
 */
public class SessionModel extends AbstractModel implements IGameStatistic {
    private SessionDao sDao;
    private QuestionDao qDao;

    private long startTime;
    private long endTime;
    private boolean isFinished;

    private int current;
    private List<QuestionModel> questions;
    private QuestionModel activeQuestion;

    public SessionModel(List<CardModel> cards, Context context){
        questions = new LinkedList<>();
        for(CardModel card : cards){
            questions.add(new QuestionModel(this, card));
        }
        current = 0;
        this.sDao = DatabaseHelper.getSessionDao(context.getApplicationContext());
        this.qDao = DatabaseHelper.getQuestionDao(context.getApplicationContext());

    }

    public void addQuestion(QuestionModel q){
        questions.add(q);
    }

    public void start(){
        startTime = new Date().getTime();
        sDao.insertSession(this);
    }

    public QuestionModel nextQuestion(){
        QuestionModel currentQuestion = questions.get(current);
        current++;
        activeQuestion = currentQuestion;
        return currentQuestion.ask();
    }

    public boolean hasNext(){
        return current < questions.size();
    }

    public void finish(){
        isFinished = true;
        endTime = new Date().getTime();
    }

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
