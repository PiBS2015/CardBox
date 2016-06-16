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
 * Diese Klasse verbindet den View mit dem Model. Hier können Services angefordert werden,
 * die im Datenbanklayer bearbeitet werden.
 */
public class ServiceProvider {

    private ServiceProvider(){
    }

    /**
     * Liefert alle Sets der Applikation zurück. Diese Sets sind noch leer und können mit
     * GetListOfCards gefüllt werden.
     * @param context der Applikationskontext
     * @return Liste von Sets
     */
    public static List<SetModel> getListOfSets(Context context){
        SetDao sDao = DatabaseHelper.getSetDao(context);
        return sDao.getAllSets();
    }

    /**
     * Liefert alle Karten eines Sets zurück.
     * @param context der Applikationskontext
     * @return Liste von Karten
     */
    public static List<CardModel> getListOfCards(Context context, SetModel set){
        CardDao cDao = DatabaseHelper.getCardDao(context);
        return cDao.getAllCards(set);
    }

    public static void fillSet(Context context, SetModel set){
        SetDao sDao = DatabaseHelper.getSetDao(context);
        sDao.fillSet(set);
    }

    /**
     * Löscht ein Set mit allen Karten.
     * @param context der Applikationskontext
     * @param set das zu löschende Set
     */
    public static void clearSet(Context context, SetModel set){
        CardDao cDao = DatabaseHelper.getCardDao(context);
        for(CardModel card : getListOfCards(context, set)){
            cDao.deleteCard(card);
        }
        SetDao sDao = DatabaseHelper.getSetDao(context);
        sDao.fillSet(set);
    }

    /**
     * Speichert ein neues Set
     * @param context der Kontext
     * @param set das Set
     */
    public static void enterNewSet(Context context, SetModel set){
        DatabaseHelper.getSetDao(context).updateOrInsertSet(set);
    }

    /**
     * Speichert eine neue Karte
     * @param context der Kontext
     * @param card die Karte
     */
    public static void enterNewCard(Context context, CardModel card){
        DatabaseHelper.getCardDao(context).insertCard(card);
    }

    /**
     * Aktualisiert die Karte im Back End
     * @param context der Kontext
     * @param card die Karte
     */
    public static void updateCard(Context context, CardModel card){
        DatabaseHelper.getCardDao(context).updateCard(card);
    }

    public static void updateSet(Context context, SetModel set){
        DatabaseHelper.getSetDao(context).updateOrInsertSet(set);
    }

}
