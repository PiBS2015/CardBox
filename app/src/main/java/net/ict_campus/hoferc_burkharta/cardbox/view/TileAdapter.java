package net.ict_campus.hoferc_burkharta.cardbox.view;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.ict_campus.hoferc_burkharta.cardbox.R;

/**
 * Created by Hoferc on 02.06.2016.
 */
public class TileAdapter extends BaseAdapter {
    public Context mContext;

    private int[] tileColor;
    private int[] textColor;

    private int currentColor[] = new int[2];

    public TileAdapter(Context c) {
        mContext = c;

        Resources r = c.getResources();
        tileColor = new int[] {r.getColor(R.color.colorSecondaryDark), r.getColor(R.color.colorPrimaryLight), r.getColor(R.color.colorPrimaryLighter)};
        textColor = new int[] {r.getColor(R.color.colorPrimaryLighter), r.getColor(R.color.colorSecondaryDarker), r.getColor(R.color.colorPrimaryDark)};
        currentColor[0] = tileColor[0];
        currentColor[1] = textColor[0];
    }

    @Override
    public int getCount() {
        return mFiller.length + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
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

        if (position == mFiller.length) {
            if (position%2 == 0) {
                textView.setLayoutParams(new GridView.LayoutParams(size*2, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 64, mContext.getResources().getDisplayMetrics())));
            }
            textView.setBackgroundColor(tileColor[2]);
            textView.setTextColor(textColor[2]);
            textView.setText("Add a new Set!");
        } else {
            textView.setText(mFiller[position]);
        }
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

    public String[] mFiller = {
            "Hallo", "Welt", "123", "Echo", "Baum", "Test", "Gugus", "Alpha Beta Gamma Delta Epsilon Etha Theta Zeta Iota Kappa Lambda My Ny Xi Omikron Pi Rho Sigma Tau", "Superkalifragilistikexpialigetisch"
    };
}
