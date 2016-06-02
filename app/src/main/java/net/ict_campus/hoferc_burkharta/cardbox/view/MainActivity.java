package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.DatabaseHelper;
import net.ict_campus.hoferc_burkharta.cardbox.model.ModelInitializer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ModelInitializer.initializeModel(this);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getWritableDatabase();

        db.execSQL("INSERT INTO 'SEITE' VALUES (null, 'First Set')");
        Cursor c = db.rawQuery("SELECT name FROM 'SET'", null);
        c.moveToFirst();
        Log.d(this.getClass().getSimpleName(), c.getString(0));
        db.close();
    }


}
