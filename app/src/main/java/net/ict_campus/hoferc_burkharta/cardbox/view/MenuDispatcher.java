package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import net.ict_campus.hoferc_burkharta.cardbox.R;

/**
 * Created by hoferc on 16.06.2016.
 */
public class MenuDispatcher {
    public static boolean dispatch(MenuItem item, Context c) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.action_gotomain:
                intent = new Intent(c, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                c.startActivity(intent);
                return true;
            case R.id.action_editablesetlist:
                intent = new Intent(c, ListDetailActivity.class);
                c.startActivity(intent);
                return true;
            case R.id.action_newset:
                intent = new Intent(c, EditSetActivity.class);
                c.startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
