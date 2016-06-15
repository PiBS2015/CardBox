package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.ict_campus.hoferc_burkharta.cardbox.model.game.QuestionModel;

/**
 * Created by Burkharta on 03.06.2016.
 */
public class QuestionDao extends  AbstractDao{

    public QuestionDao(Context context){
        super(context);
    }

    public void insertQuestion(QuestionModel question){
        SQLiteDatabase db = openDatabase(true);

        long fkCard = getIdOfObject(question.getCard());
        long fkSession = getIdOfObject(question.getOwningSession());
        long time = question.getPassedTime();

        ContentValues contVals = new ContentValues();
        contVals.put(QuestionSchema.COL_DAUER, time + "");
        contVals.put(QuestionSchema.COL_KARTE, fkCard + "");
        contVals.put(QuestionSchema.COL_RICHTIG, question.isCorrect());
        contVals.put(QuestionSchema.COL_SESSION, fkSession);

        long id = db.insertOrThrow(QuestionSchema.TABLE_NAME, null, contVals);

        setIdOfObject(question, id);
    }

}
