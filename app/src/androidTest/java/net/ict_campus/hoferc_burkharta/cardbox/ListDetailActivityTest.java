package net.ict_campus.hoferc_burkharta.cardbox;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import net.ict_campus.hoferc_burkharta.cardbox.model.ServiceProvider;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;
import net.ict_campus.hoferc_burkharta.cardbox.view.ListDetailActivity;

/**
 * Created by hoferc on 16.06.2016.
 */
public class ListDetailActivityTest extends ActivityUnitTestCase<ListDetailActivity> {

    private ListView setList;
    private DatabaseHelper db;
    private Context context;

    public ListDetailActivityTest() {
        super(ListDetailActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        context = this.getInstrumentation().getContext();

        SetModel s = new SetModel("TestSet");
        // Nullpointer, weiss nicht wieso
        SQLiteDatabase dBase = db.getWritableDatabase();
        ServiceProvider.enterNewSet(context, s);
    }

    @Override
    protected void setActivity(Activity testActivity) {
        if (testActivity!=null) testActivity.setTheme(R.style.AppTheme);
        super.setActivity(testActivity);
    }

    public void testSetListView_CountShouldBeOne() {
        int actual = setList.getCount();

        assertEquals(1, actual);
    }

    public void testSetListView_ValidateSetName() {
        String actual = (String)setList.getItemAtPosition(0);
        assertEquals("TestSet", actual);
    }
}
