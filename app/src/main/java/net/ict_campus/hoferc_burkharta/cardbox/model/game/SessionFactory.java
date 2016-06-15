package net.ict_campus.hoferc_burkharta.cardbox.model.game;

import android.content.Context;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Burkharta on 15.06.2016.
 */
public class SessionFactory {

    private Context context;

    public enum Mode{
        RANDOM, FIXED
    }

    public SessionFactory(Context context){
        this.context = context;
    }

    public SessionModel createSession(Mode mode, SetModel set){
        switch(mode){
            case FIXED:
                List<CardModel> cards = set.getCards();
                return new SessionModel(cards, context);
            default:
                // Not yet implemented
                return null;
        }
    }
}
