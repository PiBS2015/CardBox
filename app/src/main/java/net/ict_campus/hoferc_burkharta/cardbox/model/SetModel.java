package net.ict_campus.hoferc_burkharta.cardbox.model;

import android.content.Context;

import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.AbstractModel;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.CardDao;
import net.ict_campus.hoferc_burkharta.cardbox.model.dbUtils.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Eine Implementierung eines Sets. Ein solches Set hat einen Namen und mehrere normale Karten in keiner
 * Besonderen Reihenfolge.
 */
public class SetModel extends BaseDisplayModel {
    private String name;
    private List<CardModel> content;
    //Not used atm.
    private String styleRessource;
    private String pictureRessource;

    /**
     * Erstellt ein neues, leeres Set
     * @param name Name des Sets
     */
    public SetModel(String name){
        this.name = name;
        this.content = new ArrayList<>();
    }

//    /**
//     * Erzeugt ein Set mit
//     * @param name
//     * @param content
//     */
//    SetModel(String name, Collection<CardModel> content){
//        this.content = new ArrayList<>(content);
//        this.name = name;
//    }

    /**
     * Fügt dem Set eine neue Karte hinzu
     * @param cardToAdd die neue Karte
     */
    public void addCard(CardModel cardToAdd){
        content.add(cardToAdd);
    }

    /**
     * Setzt den Karteninhalt der Liste neu
     * @param cardsToAdd die neue KArtenliste
     */
    public void setCards(List<CardModel> cardsToAdd){
        this.content = cardsToAdd;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public List<CardModel> getCards() {
        return this.content;
    }

    /*
    Not used at the moment
     */
    public String getStyleRessource() {
        return this.styleRessource;
    }

    public String getPictureRessource() {
        return this.pictureRessource;
    }
}
