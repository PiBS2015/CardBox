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

    public static List<SetModel> getListOfSets(Context context){
        SetDao sDao = DatabaseHelper.getSetDao(context);
        return sDao.getAllSets();
    }

    public static List<CardModel> getListOfCards(Context context, SetModel set){
        CardDao cDao = DatabaseHelper.getCardDao(context);
        return cDao.getAllCards(set);
    }

    public static void clearSet(Context context, SetModel set){
        CardDao cDao = DatabaseHelper.getCardDao(context);
        for(CardModel card : getListOfCards(context, set)){
            cDao.deleteCard(card);
        }
        SetDao sDao = DatabaseHelper.getSetDao(context);
        sDao.fillSet(set);
    }

    public static void enterNewSet(Context context, SetModel set){
        DatabaseHelper.getSetDao(context).updateOrInsertSet(set);
    }

    public static void enterNewCard(Context context, CardModel card){
        DatabaseHelper.getCardDao(context).insertCard(card);
    }

    public static void updateCard(Context context, CardModel card){
        DatabaseHelper.getCardDao(context).updateCard(card);
    }

}
