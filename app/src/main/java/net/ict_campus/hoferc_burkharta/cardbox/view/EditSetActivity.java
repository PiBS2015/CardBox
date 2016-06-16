package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

public class EditSetActivity extends AppCompatActivity {

    Toolbar toolbar;
    SetModel set;
    EditText text;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_set);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.set = (SetModel) intent.getSerializableExtra("model");

        this.text = (EditText) findViewById(R.id.edit_set_text);
        this.saveButton = (Button) findViewById(R.id.save_set_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSet(v);
            }
        });

    }

    private void saveSet(View v){
        if(set == null){
            ServiceProvider.enterNewSet(this, new SetModel(this.text.getText() + ""));
        }
        else {
            set.setName(this.text.getText() + "");
            ServiceProvider.updateSet(this, set);
        }
        Intent intent = new Intent(this, ListDetailActivity.class);
        this.startActivity(intent);
    }
}
