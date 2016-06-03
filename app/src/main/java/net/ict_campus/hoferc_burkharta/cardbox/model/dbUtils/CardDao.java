package net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.ict_campus.hoferc_burkharta.cardbox.model.AbstractFace;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.ICardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burkharta on 03.06.2016.
 */
public class CardDao extends AbstractDao {

    public CardDao(Context context){
        super(context);
    }

    public void insertCard(CardModel card, SetModel set){
        SQLiteDatabase db = openDatabase(true);

        AbstractFace front = (AbstractFace) card.getFace(CardSide.FRONT);
        AbstractFace back = (AbstractFace) card.getFace(CardSide.BACK);
        long setId = getIdOfObject(set);

        Cursor result = db.query(
                SetSchema.TABLE_NAME,
                new String[]{SetSchema.COL_ID},
                "? = " + SetSchema.COL_ID,
                new String[]{""+setId},
                null, null, null);

        if(result.getCount() != 1){
            db.close();
            throw new RuntimeException("Das Set " + set + " gibt es nicht!");
        }

        long frontId = db.insertOrThrow(FaceSchema.TABLE_NAME, null, getFaceVals(front));
        long backId = db.insertOrThrow(FaceSchema.TABLE_NAME, null, getFaceVals(front));

        long cardId = db.insertOrThrow(CardSchema.TABLE_NAME, null, getCardVals(card, setId, frontId, backId));

        setIdOfObject(card, cardId);
        setIdOfObject(front, frontId);
        setIdOfObject(back, backId);

        db.close();
    }

    private ContentValues getFaceVals(AbstractFace face){
        ContentValues frontFaceVals = new ContentValues();
        String[] ressources = face.getRessource();

        frontFaceVals.put(FaceSchema.COL_TEXT, face.getRessource()[0]);
        frontFaceVals.put(FaceSchema.COL_BILD, face.getRessource()[1]);

        return frontFaceVals;
    }

    private ContentValues getCardVals(ICardModel card, long idSet, long idFront, long idBack){
        ContentValues cardVals = new ContentValues();

        cardVals.put(CardSchema.COL_DESCRIPTION, card.getDescription());
        cardVals.put(CardSchema.COL_SET_KEY, idSet);
        cardVals.put(CardSchema.COL_FRONT_FACE, idFront);
        cardVals.put(CardSchema.COL_BACK_FACE, idBack);

        return cardVals;
    }

    public void updateCard(CardModel card){
        if(!card.isInDatabase()){
            throw new RuntimeException("Die Karte " + card +" kann nicht updated werden, " +
                    "sie ist nicht in der Datenbank gespeichert!");
        }
        SQLiteDatabase db = openDatabase(true);

        Cursor result = db.query(
                CardSchema.TABLE_NAME,
                new String[]{CardSchema.COL_ID},
                CardSchema.COL_ID + " = ?",
                new String[]{getIdOfObject(card) + ""},
                null, null, null
        );

        db.close();
    }

    public List<ICardModel> getAllCards(SetModel ofSet){
        SQLiteDatabase db = openDatabase(false);
        List<ICardModel> cards = new ArrayList<>();

        AbstractModel dbLayerSet = ofSet;
        long setId = dbLayerSet.getId();

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
                CardBuilder builder = new CardBuilder();
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
