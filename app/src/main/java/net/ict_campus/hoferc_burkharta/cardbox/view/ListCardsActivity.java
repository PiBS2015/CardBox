package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.List;

public class ListCardsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SetModel set;
    private List<CardModel> listOfCards;
    private Button createNewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        set = (SetModel) intent.getSerializableExtra("model");
        setContentView(R.layout.activity_list_detail);

        createNewCard = (Button) findViewById(R.id.new_list_button);
        createNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildNewCard();
            }
        });

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void buildNewCard(){
        CardModel card = new CardBuilder(set)
                .build();
        Intent intent = new Intent(this, EditCardActivity.class);
        intent.putExtra("model", card);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        listOfCards = ServiceProvider.getListOfCards(this, set);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ListAdapter(this, listOfCards, ViewFullCardActivity.class, ViewFullCardActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_list_detail_activity) {
            Intent intent = new Intent(this, ListDetailActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_edit_card_activity) {
            Intent intent = new Intent(this, EditCardActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
