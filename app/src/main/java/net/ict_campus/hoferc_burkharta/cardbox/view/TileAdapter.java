package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import net.ict_campus.hoferc_burkharta.cardbox.R;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Hoferc on 02.06.2016.
 */
public class TileAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<SetModel> content;
    private HashMap<TextView, SetModel> contentMap;

    private int[] tileColor;
    private int[] textColor;

    private int currentColor[] = new int[2];

    public TileAdapter(Context c, List<SetModel> content) {
        mContext = c;
        this.content = content;
        contentMap = new HashMap<>();

        Resources r = c.getResources();
        tileColor = new int[] {r.getColor(R.color.colorSecondaryDark), r.getColor(R.color.colorPrimaryLight), r.getColor(R.color.colorPrimaryLighter)};
        textColor = new int[] {r.getColor(R.color.colorPrimaryLighter), r.getColor(R.color.colorSecondaryDarker), r.getColor(R.color.colorPrimaryDark)};
        currentColor[0] = tileColor[0];
        currentColor[1] = textColor[0];
    }

    @Override
    public int getCount() {
        return content.size() + 1;
    }

    @Override
    public SetModel getItem(int position) {
        if(position != content.size()) {
            return content.get(position);
        }
        else{
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        GridView grid = (GridView) parent;
        int size = grid.getColumnWidth();

        if (convertView == null) {
            textView = new TextView(mContext);

            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            textView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, size));
            textView.setGravity(Gravity.CENTER);
        } else {
            textView = (TextView) convertView;
        }

        if (position == 0 || (position + 1) % 4 == 0 || (position + 1) % 4 == 1) {
            currentColor[0] = tileColor[1];
            currentColor[1] = textColor[1];
        } else {
            currentColor[0] = tileColor[0];
            currentColor[1] = textColor[0];
        }

        textView.setBackgroundColor(currentColor[0]);
        textView.setTextColor(currentColor[1]);

        if (position == content.size()) {
            if (position%2 == 0) {
                textView.setLayoutParams(new GridView.LayoutParams(size*2, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 64, mContext.getResources().getDisplayMetrics())));
            }
            textView.setBackgroundColor(tileColor[2]);
            textView.setTextColor(textColor[2]);
            textView.setText("Add a new Set!");
        } else {
            SetModel obj = content.get(position);
            contentMap.put(textView, obj);
            textView.setText(obj.getName());
        }
        textView.setClickable(true);
        textView.setOnClickListener(this);

        return textView;
    }

    /**
     * Code-Snippet von
     * http://stackoverflow.com/questions/17408178/setlayoutparams-dip-values
     *
     * @param dimensionDp   Eine bestimmte Grösse in DP
     * @return              Diese Grösse in PX
     */
    private int getPx(int dimensionDp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }

//    public String[] mFiller = {
//            "Hallo", "Welt", "123", "Echo", "Baum", "Test", "Gugus", "Alpha Beta Gamma Delta Epsilon Etha Theta Zeta Iota Kappa Lambda My Ny Xi Omikron Pi Rho Sigma Tau", "Superkalifragilistikexpialigetisch"
//    };

    @Override
    public void onClick(View v) {
        Intent intent = null;
        TextView txt = (TextView) v;
        SetModel set = this.contentMap.get(txt);
        if(set == null) {
            intent = new Intent(mContext, ListDetailActivity.class);
        }
        else{
            CardModel newCard =new CardBuilder(set)
                    .setFaceText(CardSide.FRONT, "Front")
                    .setFaceText(CardSide.BACK, "Back")
                    .build();
            ServiceProvider.enterNewCard(mContext, newCard);
            intent = new Intent(mContext, ListCardsActivity.class);
            intent.putExtra("set", set);
        }
        mContext.startActivity(intent);

    }
}
