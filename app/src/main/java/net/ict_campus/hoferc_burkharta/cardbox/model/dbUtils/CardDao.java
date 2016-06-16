package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ICardSideModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Das Database Access Object einer Karte. Diese Klasse bietet grundlegende CRUD-Funktionalität
 */
public class CardDao extends AbstractDao {

    /**
     * Erzeugt ein neues CardDao mit gegebenem Kontext. Wird vom DatabaseHelper aufgerufen
     * @param context
     */
    CardDao(Context context){
        super(context);
    }

    /**
     * speichert ein CardModel in der Datenbank.
     * @param card das zu speichernde CardModel
     */
    public void insertCard(CardModel card){
        SQLiteDatabase db = openDatabase(true);

        SetModel set = card.getOwner();
        ICardSideModel front = (ICardSideModel) card.getFace(CardSide.FRONT);
        ICardSideModel back = (ICardSideModel) card.getFace(CardSide.BACK);
        long setId = getIdOfObject(set);

        Cursor result = db.query(
                SetSchema.TABLE_NAME,
                new String[]{SetSchema.COL_ID},
                "? = " + SetSchema.COL_ID,
                new String[]{""+setId},
                null, null, null);

        if(result.getCount() != 1){
            result.close();
            db.close();
            throw new RuntimeException("Das Set " + set + " gibt es nicht!");
        }

        result.close();

        long frontId = db.insertOrThrow(FaceSchema.TABLE_NAME, null, getFaceVals(front));
        long backId = db.insertOrThrow(FaceSchema.TABLE_NAME, null, getFaceVals(back));

        long cardId = db.insertOrThrow(CardSchema.TABLE_NAME, null, getCardVals(card, frontId, backId));

        setIdOfObject(card, cardId);
        setIdOfObject((AbstractModel) front, frontId);
        setIdOfObject((AbstractModel) back, backId);

        db.close();
    }

    /**
     * Extrahiert die Karteninformationen aus dem Face der Karte
     * @param face die Kartenseite
     * @return die ContentValues des face
     */
    private ContentValues getFaceVals(ICardSideModel face){
        ContentValues frontFaceVals = new ContentValues();
        String[] ressources = face.getRessource();
        Log.d("FACE", ressources[0]);

        frontFaceVals.put(FaceSchema.COL_TEXT, face.getRessource()[0]);
        frontFaceVals.put(FaceSchema.COL_BILD, face.getRessource()[1]);

        return frontFaceVals;
    }

    /**
     * Liefert den kartenspezifischen Content der Karte (Description, idSet, idFront, isBack)
     * @param card die Karte
     * @param idFront ID des Front-faces
     * @param idBack ID des Back-faces
     * @return ContentValues der Karte
     */
    private ContentValues getCardVals(CardModel card, long idFront, long idBack){
        long idSet = getIdOfObject(card.getOwner());
        Log.d("SETID", idSet + "");
        ContentValues cardVals = new ContentValues();

        cardVals.put(CardSchema.COL_DESCRIPTION, card.getDescription());
        cardVals.put(CardSchema.COL_SET_KEY, idSet);
        cardVals.put(CardSchema.COL_FRONT_FACE, idFront);
        cardVals.put(CardSchema.COL_BACK_FACE, idBack);

        return cardVals;
    }

    /**
     * Passt den Eintrag in der Datenbank der gelieferten Karte an. Diese muss bereits in der Datenbank
     * sein (sprich eine gültige ID haben)
     * @param card die geänderte Karte
     */
    public void updateCard(CardModel card){

        if(!card.isInDatabase()){
            throw new RuntimeException("Die Karte " + card +" kann nicht updated werden, " +
                    "sie ist nicht in der Datenbank gespeichert!");
        }
        SQLiteDatabase db = openDatabase(true);

        //Hol die IDs der Kartenseiten und der Karte selbst
        Cursor result = db.query(
                CardSchema.TABLE_NAME,
                new String[]{CardSchema.COL_ID, CardSchema.COL_FRONT_FACE, CardSchema.COL_BACK_FACE},
                CardSchema.COL_ID + " = ?",
                new String[]{getIdOfObject(card) + ""},
                null, null, null
        );

        result.moveToFirst();

        ICardSideModel front = card.getFace(CardSide.FRONT);
        ICardSideModel back = card.getFace(CardSide.BACK);
        AbstractModel dbLayerCard = (AbstractModel) card;

        long idFront = result.getLong(1);
        long idBack = result.getLong(2);
        long idCard = result.getLong(0);

        //Generiere Inhalte der Objekte
        ContentValues frontVals = getFaceVals(front);
        ContentValues backVals = getFaceVals(back);
        ContentValues cardVals = getCardVals(card, idFront, idBack);

        //Aktualisiere die Inhalte der Kartenseiten
        db.update(FaceSchema.TABLE_NAME, frontVals, FaceSchema.COL_ID + " = ?", new String[]{idFront + ""});
        db.update(FaceSchema.TABLE_NAME, backVals, FaceSchema.COL_ID + " = ?", new String[]{idBack + ""});

        // Modified Faces may not have IDs set correctly
        setIdOfObject((AbstractModel) front, idFront);
        setIdOfObject((AbstractModel) back, idBack);

        db.update(CardSchema.TABLE_NAME, cardVals, CardSchema.COL_ID + " = ?", new String[]{idCard + ""});

        db.close();
    }

    /**
     * Liefert alle Karten eines Sets
     * @param ofSet welches Set
     * @return Liste der Karten
     */
    public List<CardModel> getAllCards(SetModel ofSet){
        SQLiteDatabase db = openDatabase(false);
        List<CardModel> cards = new ArrayList<>();
        Log.d(this.getClass().getSimpleName(), ofSet.getName());

        long setId = getIdOfObject(ofSet);

        //Hole alle Karten mit den zugehörigen Seiten
        Cursor cardCursor = db.query(
                CardSchema.TABLE_NAME +
                        " JOIN " + FaceSchema.TABLE_NAME +
                        " AS 'front'" +
                        " ON " + CardSchema.COL_FRONT_FACE + " = front." + FaceSchema.COL_ID +
                        " JOIN " + FaceSchema.TABLE_NAME +
                        " AS 'back'" +
                        " ON " + CardSchema.COL_BACK_FACE + " = back." + FaceSchema.COL_ID ,
                new String[]{"front." + FaceSchema.COL_ID,
                        "front." + FaceSchema.COL_BILD,
                        "front." + FaceSchema.COL_TEXT,
                        "back." + FaceSchema.COL_ID,
                        "back." + FaceSchema.COL_BILD,
                        "back." + FaceSchema.COL_TEXT,
                        CardSchema.COL_ID,
                        CardSchema.COL_DESCRIPTION,
                        CardSchema.COL_SET_KEY},
                CardSchema.COL_SET_KEY + " = ?",
                new String[]{setId + ""},
                null, null, null
                );
        if(cardCursor.moveToFirst()){
            do{
                //Baue die Karte zusammen
                CardBuilder builder = new CardBuilder(ofSet);
                CardModel card = builder.setFaceText(CardSide.FRONT, cardCursor.getString(2)).
                        setFacePicture(CardSide.FRONT, cardCursor.getString(1)).
                        setFaceText(CardSide.BACK, cardCursor.getString(5)).
                        setFacePicture(CardSide.BACK, cardCursor.getString(4)).
                        setDescription(cardCursor.getString(7)).
                        build();
                setIdOfObject(card, cardCursor.getInt(6));
                cards.add(card);
            } while(cardCursor.moveToNext());
        }

        db.close();
        return cards;
    }

    /**
     * Löscht die Karte aus der Datenbank. Die Karte muss bereits in der Datenbank gespeichert sein.
     * @param card die zu löschende Karte
     * @return true, falls die Löschung erfolgreich war, false sonst
     */
    public boolean deleteCard(CardModel card){

        if(!card.isInDatabase()){
            throw new RuntimeException("Die Karte " + card + " kann nicht gelöscht werden, sie ist nicht" +
                    "in der Datenbank!");
        }

        SQLiteDatabase db = openDatabase(true);

        boolean isDeleted = db.delete(CardSchema.TABLE_NAME,
                CardSchema.COL_ID + " = ?",
                new String[]{getIdOfObject(card) + ""}
        ) == 1;

        db.close();
        return isDeleted;
    }

}
