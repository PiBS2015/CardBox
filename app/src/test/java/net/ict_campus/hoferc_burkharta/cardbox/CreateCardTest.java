package net.ict_campus.hoferc_burkharta.cardbox;

import net.ict_campus.hoferc_burkharta.cardbox.model.CardBuilder;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.CardSide;
import net.ict_campus.hoferc_burkharta.cardbox.model.SetModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CreateCardTest {
    SetModel set;

    @Before
    public void setUp(){
        this.set = new SetModel("Test Set");
    }

    @Test
    public void createCard() throws Exception {
        CardModel card = new CardBuilder(this.set)
                .setFaceText(CardSide.FRONT, "Front")
                .setFaceText(CardSide.BACK, "Back")
                .build();
        assertEquals("Front", card.getFace(CardSide.FRONT).getRessource()[0]);
    }
}