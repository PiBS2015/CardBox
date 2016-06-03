package net.ict_campus.hoferc_burkharta.cardbox.view;

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
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.CardDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.SetDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ModelInitializer.initializeModel(this);
        setContentView(R.layout.activity_main);
        CardDao dao = DatabaseHelper.getCardDao(this.getApplicationContext());
        CardBuilder cb = new CardBuilder();
        cb.setFaceText(CardSide.FRONT, "Hallo");
        cb.setFaceText(CardSide.BACK, "Welt!");
        cb.setDescription("First Card");
        CardModel card = cb.build();

//        SetModel set = new SetModel("bla");
//
//        //dao.insertCard(card, set);
//        dao.deleteCard(card);
//        Log.d("Main", dao.getAllCards(set).size() + " Cards loaded");
    }


}
