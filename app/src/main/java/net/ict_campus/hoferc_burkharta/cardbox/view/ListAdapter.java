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

import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hoferc on 15.06.2016.
 */
public class ListAdapter extends BaseAdapter {

    private List<SetModel> content;
    private HashMap<TextView, SetModel> contentMap;
    Context c;

    public ListAdapter (Context c, List<SetModel> sets) {
        this.c = c;
        this.content = sets;
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public SetModel getItem(int position) {
        return content.get(position);
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

        textView.setText(content.get(position).getName());

        return textView;
    }
}
