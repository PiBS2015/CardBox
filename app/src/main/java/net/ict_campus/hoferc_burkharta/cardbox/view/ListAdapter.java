package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import net.ict_campus.hoferc_burkharta.cardbox.model.BaseDisplayModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Hoferc on 15.06.2016.
 */
public class ListAdapter extends BaseAdapter implements View.OnClickListener {

    private List<? extends BaseDisplayModel> content;
    private HashMap<TextView, BaseDisplayModel> contentMap;
    Context mContext;

    private Class<? extends Activity> targetIfHit;
    private Class<? extends Activity> targetIfMiss;

    public ListAdapter (Context c, List<? extends BaseDisplayModel> sets, Class<? extends Activity> targetIfHit, Class<? extends Activity> targetIfMiss) {
        this.mContext = c;
        this.content = sets;
        this.targetIfHit = targetIfHit;
        this.targetIfMiss = targetIfMiss;
        contentMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public BaseDisplayModel getItem(int position) {
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
            textView = new TextView(mContext);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        } else {
            textView = (TextView) convertView;
        }
        BaseDisplayModel obj = content.get(position);
        contentMap.put(textView, obj);

        textView.setText(content.get(position).getName());
        textView.setClickable(true);
        textView.setOnClickListener(this);

        return textView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        TextView txt = (TextView) v;
        BaseDisplayModel m = this.contentMap.get(txt);
        if(m == null) {
            Log.d("PRESS","Heya");
            intent = new Intent(mContext, targetIfMiss);
        }
        else{
            intent = new Intent(mContext, targetIfHit);
            intent.putExtra("model", m);
            Log.d("PRESS","Heyo");
        }
        mContext.startActivity(intent);
    }
}
