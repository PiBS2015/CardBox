package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<SetModel> listOfSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listOfSets = ServiceProvider.getListOfSets(this);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new TileAdapter(this, listOfSets));

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
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
        return super.onOptionsItemSelected(item);
    }
}
