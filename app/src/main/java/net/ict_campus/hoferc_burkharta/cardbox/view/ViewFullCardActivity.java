package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;

public class ViewFullCardActivity extends AppCompatActivity {

    Toolbar toolbar;
    CardModel card;
    TextView front;
    TextView back;
    TextView setName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_card);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.card = (CardModel) intent.getSerializableExtra("model");

        front = (TextView) findViewById(R.id.full_view_front);
        back = (TextView) findViewById(R.id.full_view_back);
        setName = (TextView) findViewById(R.id.set_name);

        front.setText(card.getFace(CardSide.FRONT).getRessource()[0]);
        back.setText(card.getFace(CardSide.BACK).getRessource()[0]);
        setName.setText(card.getOwner().getName());

        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCard(CardSide.FRONT);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCard(CardSide.BACK);
            }
        });
    }

    private void editCard(CardSide whichSideFirst){
        Intent intent = new Intent(this, EditCardActivity.class);
        intent.putExtra("model", card);
        intent.putExtra("face", whichSideFirst.toString());
        startActivity(intent);
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
