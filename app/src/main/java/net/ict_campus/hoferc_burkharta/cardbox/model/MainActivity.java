package net.ict_campus.hoferc_burkharta.cardbox.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ICardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.ISetModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.CardDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.SetDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionFactory;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ModelInitializer.initializeModel(this);
        setContentView(R.layout.activity_main);

//        SetModel set = new SetModel("bla");
//
//        //dao.insertCard(card, set);
//        dao.deleteCard(card);
        SetDao setDao = DatabaseHelper.getSetDao(this.getApplicationContext());
        List<SetModel> sets = setDao.getAllSets();
        SetModel activeSet = sets.get(0);
        CardBuilder builder = new CardBuilder(activeSet);
        builder.setDescription("Text");
        builder.setFaceText(CardSide.FRONT, "Front Text");
        CardModel newCard = builder.build();
        CardDao dao = DatabaseHelper.getCardDao(this);
        dao.insertCard(newCard);
        setDao.fillSet(activeSet);

        SessionFactory fac = new SessionFactory(this);
        SessionModel se = fac.createSession(SessionFactory.Mode.FIXED, activeSet);

        int counter = 0;

        se.start();

        while(se.hasNext()){
            counter++;
            se.nextQuestion();
            se.answerActiveQuestion(true);
        }

        se.finish();

        Log.d(this.getClass().getSimpleName(), "Game finished: " + counter + " Cards played, " + sets.get(0).getCards().size() + " Cards available");
        Log.d(this.getClass().getSimpleName(), "Time played: " + se.getPassedTime());

//        List<ICardModel> cards = dao.getAllCards(sets.get(0));
//        Log.d(this.getClass().getSimpleName(), cards.get(0).getDescription());
//        Log.d(this.getClass().getSimpleName(), cards.get(0).getFace(CardSide.FRONT).getRessource()[0]);
//        CardBuilder builder = new CardBuilder(cards.get(0));
//        builder.setDescription("Text");
//        builder.setFaceText(CardSide.FRONT, "Front Text");
//        cards.set(0, builder.build());
//
//
//        Log.d(this.getClass().getSimpleName(), cards.get(0).getDescription());
//        Log.d(this.getClass().getSimpleName(), cards.get(0).getFace(CardSide.FRONT).getRessource()[0]);
//
//        Log.d("Main", sets.size() + " Sets loaded");
//        Log.d("Main", dao.getAllCards(sets.get(0)).size() + "");

    }


}
