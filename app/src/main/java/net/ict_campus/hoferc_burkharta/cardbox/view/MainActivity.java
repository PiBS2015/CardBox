package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.ModelInitializer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModelInitializer.initializeModel(this);
        setContentView(R.layout.activity_main);
    }


}
