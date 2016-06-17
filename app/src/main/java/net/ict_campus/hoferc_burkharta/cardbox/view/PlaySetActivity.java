package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.QuestionModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionFactory;
import net.ict_campus.hoferc_burkharta.cardbox.model.game.SessionModel;

public class PlaySetActivity extends AppCompatActivity {

    Toolbar toolbar;

    private SensorManager sensorManager;
    private Sensor mySensor;
    private SensorEventListener shakeListener;


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

        this.sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        this.mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(this.mySensor != null){
            this.shakeListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float[] values = event.values;

                    if(normOfValues(values) > 3.) {
                        flip();
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
        }

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

        step();

    }

    private float normOfValues(float[] vector){
        if(vector.length != 3){
            throw new RuntimeException("falsche Länge des Wertearrays");
        }

        float sum = 0;

        for(int i = 0; i<3; i++){
            sum += Math.pow(vector[i], 2);
        }

        return (float) Math.sqrt(sum);
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(shakeListener != null){
            sensorManager.registerListener(shakeListener, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(shakeListener);
    }

    private void step(){
        if(session.hasNext()){
            this.visibleSide = CardSide.FRONT;
            currentQuestion = session.nextQuestion();
            cardDisplay.setText(currentQuestion.getDisplayRessource(visibleSide)[0]);
        }
        else{
            session.finish();
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

    protected void onDestroy(){
        super.onDestroy();
        if(currentQuestion != null){
            currentQuestion.answer(false);
        }
        if(session != null) {
            session.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(MenuDispatcher.dispatch(item, this)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
