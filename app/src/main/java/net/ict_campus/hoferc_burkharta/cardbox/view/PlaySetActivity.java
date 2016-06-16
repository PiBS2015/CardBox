package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.QuestionModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionFactory;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionModel;

public class PlaySetActivity extends AppCompatActivity {

    Toolbar toolbar;

    SessionModel session;
    QuestionModel currentQuestion;
    CardSide visibleSide;

    Button correctButton;
    Button wrongButton;
    TextView cardDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_set);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        SetModel set = (SetModel) intent.getSerializableExtra("model");
        ServiceProvider.fillSet(this, set);

        this.session = new SessionFactory(this).createSession(SessionFactory.Mode.FIXED, set);

        this.correctButton = (Button) findViewById(R.id.play_button_correct);
        this.wrongButton = (Button) findViewById(R.id.play_button_wrong);
        this.cardDisplay = (TextView) findViewById(R.id.play_card_display);

        cardDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip();
            }
        });

        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer(true);
            }
        });

        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer(false);
            }
        });

        session.start();

        Log.d(this.getClass().getSimpleName(), "I made it");
        step();

    }

    private void step(){
        if(session.hasNext()){
            this.visibleSide = CardSide.FRONT;
            currentQuestion = session.nextQuestion();
            cardDisplay.setText(currentQuestion.getDisplayRessource(visibleSide)[0]);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void answer(boolean isCorrect){
        Log.d(this.getClass().getSimpleName(), isCorrect + "");
        currentQuestion.answer(isCorrect);
        step();
    }

    private void flip(){
        cardDisplay.setText(currentQuestion.flip(visibleSide)[0]);
        this.visibleSide = visibleSide.opposite();
    }

}
