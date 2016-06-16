package net.ict_campus.hoferc_burkharta.cardbox.model;

import android.util.Log;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.CardDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;

/**
 * Created by Burkharta on 01.06.2016.
 */
public class CardBuilder {
    CardModel card;
    String frontText;
    String backText;
    String frontPictureRessource;
    String backPictureRessource;

    public CardBuilder(SetModel set){
        this.card = new CardModel(set);
    }

    public CardBuilder(CardModel card){
        this.card = card;

        // Lade die Inhalte der Karte
        extractRessources(CardSide.FRONT);
        extractRessources(CardSide.BACK);
    }

    private void extractRessources(CardSide whichSide){
        String[] ressources = card.getFace(whichSide).getRessource();
        switch(whichSide){
            case FRONT:
                frontText = ressources[0];
                if(ressources.length >= 2) frontPictureRessource = ressources[1];
                break;
            case BACK:
                backText = ressources[0];
                if(ressources.length >= 2) backPictureRessource = ressources[1];
                break;
        }
    }

    public CardBuilder setDescription(String description){
        this.card.setDescription(description);
        return this;
    }

    public CardBuilder setNumber(String number){
        this.card.setNumber(number);
        return this;
    }

    public CardBuilder setFaceText(CardSide whichSide, String text){

        switch(whichSide){
            case FRONT: frontText = text;
                break;
            case BACK: backText = text;
                break;
        }
        return this;
    }

    public CardBuilder setFacePicture(CardSide whichSide, String ressource){
        switch (whichSide){
            case FRONT: frontPictureRessource = ressource;
                break;
            case BACK: backPictureRessource = ressource;
                break;
        }
        return this;
    }

    public CardModel build(){
        ICardSideModel[] faces = generateFaces();
        card.setFrontFace(faces[0]);
        card.setBackFace(faces[1]);

        return card;
    }

    private ICardSideModel[] generateFaces(){
        ICardSideModel[] faces = new ICardSideModel[2];
        if(isPictureFace(CardSide.FRONT)){
            faces[0] = new PictureCardFace(frontPictureRessource, frontText);
        } else{
            faces[0] = new TextCardFace(frontText);
        }
        if(isPictureFace(CardSide.BACK)){
            faces[1] = new PictureCardFace(backPictureRessource, backText);
        } else{
            faces[1] = new TextCardFace(backText);
        }
        return faces;
    }

    private boolean isPictureFace(CardSide side){
        switch(side){
            case FRONT:
                return this.frontPictureRessource != null;
            case BACK:
                return this.backPictureRessource != null;
        }
        return false;
    }

}
