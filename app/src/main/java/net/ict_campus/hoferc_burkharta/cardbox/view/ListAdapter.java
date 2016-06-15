package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.app.ListActivity;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoferc on 15.06.2016.
 */
public class ListAdapter extends BaseAdapter {

    public String[] mFiller = {
            "Hallo", "Welt", "123", "Echo", "Baum", "Test", "Gugus", "Alpha Beta Gamma Delta Epsilon Etha Theta Zeta Iota Kappa Lambda My Ny Xi Omikron Pi Rho Sigma Tau", "Superkalifragilistikexpialigetisch"
    };
    Context c;

    public ListAdapter (Context c) {
        this.c = c;
    }

    @Override
    public int getCount() {
        return mFiller.length;
    }

    @Override
    public Object getItem(int position) {
        return mFiller[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        ListView list = (ListView) parent;

        if (convertView == null) {
            textView = new TextView(c);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        } else {
            textView = (TextView) convertView;
        }

        textView.setText(mFiller[position]);

        return textView;
    }
}
