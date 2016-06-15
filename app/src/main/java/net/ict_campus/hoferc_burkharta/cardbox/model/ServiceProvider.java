package net.ict_campus.hoferc_burkharta.cardbox.model;

import android.content.Context;
import android.content.pm.PackageInstaller;
import android.provider.ContactsContract;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.CardDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.QuestionDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.SessionDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.SetDao;

import java.util.List;

/**
 * Created by Burkharta on 27.05.2016.
 */
public class ServiceProvider {

    private ServiceProvider(){
    }

    public static List<ISetModel> getListOfSets(Context context){
        SetDao sDao = DatabaseHelper.getSetDao(context);
        return sDao.getAllSets();
    }

    public static void enterNewSet(Context context, SetModel set){
        DatabaseHelper.getSetDao(context).updateOrInsertSet(set);
    }

}
