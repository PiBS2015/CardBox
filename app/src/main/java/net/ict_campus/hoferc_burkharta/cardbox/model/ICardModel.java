package net.ict_campus.hoferc_burkharta.cardbox.model;

/**
 * Created by Burkharta on 27.05.2016.
 */
public interface ICardModel {
    ICardSideModel getFace(CardSide which);
    String getDescription();
    String getNumber();
}
