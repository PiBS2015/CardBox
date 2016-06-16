package net.ict_campus.hoferc_burkharta.cardbox;

import android.app.Activity;
import android.content.Intent;
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

    public ListDetailActivityTest() {
        super(ListDetailActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

//        SQLiteDatabase db = ;
//        DatabaseHelper.createDB(db);

        SetModel s = new SetModel("TestSet");
        ServiceProvider.enterNewSet(getActivity(), s);

        setList = (ListView) getActivity().findViewById(R.id.list_view);
        startActivity(new Intent(getInstrumentation().getTargetContext(), ListDetailActivity.class), null, null);

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
