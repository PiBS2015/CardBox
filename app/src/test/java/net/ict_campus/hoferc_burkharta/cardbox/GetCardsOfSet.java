package net.ict_campus.hoferc_burkharta.cardbox;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Burkharta on 17.06.2016.
 */
public class GetCardsOfSet {
    SetModel set;
    CardModel card;

    @Before
    public void setUp(){
        this.set = new SetModel("Test Set");
        this.card = new CardBuilder(this.set)
                .setFaceText(CardSide.FRONT, "Front")
                .setFaceText(CardSide.BACK, "Back")
                .build();
        assertEquals("Front", card.getFace(CardSide.FRONT).getRessource()[0]);
    }

    @Test
    public void insertCard(){
        this.set.addCard(this.card);
        assertEquals(set.getCards().size(), 1);
    }
}
