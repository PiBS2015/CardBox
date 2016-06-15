package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;

public class EditCardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView editText;

    private CardModel card;
    private CardSide visibleSide = CardSide.FRONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        Button flipButton = (Button) findViewById(R.id.flip_card_button);
        Button saveButton = (Button) findViewById(R.id.save_card_button);

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveCard();
            }
        });

        Intent intent = getIntent();
        card = (CardModel) intent.getSerializableExtra("model");


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.editText = (TextView) findViewById(R.id.card_edit_text);
        setUpCard();

    }

    private void setUpCard(){
        Log.d("FLIP", "should flip " + visibleSide + card.getFace(visibleSide).getRessource()[0]);
        this.editText.setText("");
        this.editText.setText(card.getFace(visibleSide).getRessource()[0]);
    }

    private void flipCard(){
        this.visibleSide = visibleSide.opposite();
        setUpCard();
    }

    private void saveCard(){
        ServiceProvider.updateCard(this, this.card);
        Toast.makeText(this, "Änderungen gespeichert", Toast.LENGTH_SHORT).show();
    }


}
