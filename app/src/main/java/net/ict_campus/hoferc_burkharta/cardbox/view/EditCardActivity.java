package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;

public class EditCardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView editText;
    private TextView nameOfSet;

    private CardModel card;
    private CardBuilder builder;
    private CardSide visibleSide = CardSide.FRONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        Button flipButton = (Button) findViewById(R.id.flip_card_button);
        Button saveButton = (Button) findViewById(R.id.save_card_button);

        if (visibleSide == CardSide.FRONT) {
            flipButton.setText(R.string.chg_to_backside);
        } else {
            flipButton.setText(R.string.chg_to_frontside);
        }


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

        TextView setName = (TextView) findViewById(R.id.set_name);
        setName.setText(card.getOwner().getName());

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.editText = (TextView) findViewById(R.id.card_edit_text);
        setUpCard();

    }

    @Override
    protected void onResume(){
        super.onResume();
        builder = new CardBuilder(card);
    }

    @Override
    protected void onPause(){
        super.onPause();
        card = builder.build();
    }

    private void setUpCard(){
        this.editText.setText(card.getFace(visibleSide).getRessource()[0]);
    }

    private void flipCard(){
        builder.setFaceText(visibleSide, editText.getText() + "");
        this.visibleSide = visibleSide.opposite();
        setUpCard();
    }

    private void saveCard(){
        builder.setFaceText(visibleSide, editText.getText() + "");
        card = builder.build();
        ServiceProvider.updateCard(this, this.card);
        Toast.makeText(this, "Änderungen gespeichert: " + card.getFace(CardSide.FRONT).getRessource()[0], Toast.LENGTH_SHORT).show();
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
