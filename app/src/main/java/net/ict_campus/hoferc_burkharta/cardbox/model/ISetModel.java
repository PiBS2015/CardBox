package net.ict_campus.hoferc_burkharta.cardbox.model;

import java.util.List;

/**
 * Created by Burkharta on 27.05.2016.
 */
public interface ISetModel {
    String getName();
    List<CardModel> getCards();
    //Soll & kann-Ziele
    String getStyleRessource();
    String getPictureRessource();
}
