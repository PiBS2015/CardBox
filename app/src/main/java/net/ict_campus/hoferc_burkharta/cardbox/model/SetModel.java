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
public class SetModel extends AbstractModel implements ISetModel {
    private String name;
    private List<CardModel> content;
    //Not used atm.
    private String styleRessource;
    private String pictureRessource;

    /**
     * Erstellt ein neues, leeres Set
     * @param name - Name des Sets
     */
    public SetModel(String name){
        this.name = name;
        this.content = new ArrayList<>();
    }

    /**
     * Erzeu
     * @param name
     * @param content
     */
    SetModel(String name, Collection<CardModel> content){
        this.content = new ArrayList<>(content);
        this.name = name;
    }

    public void addCard(CardModel cardToAdd){
        content.add(cardToAdd);
    }

    public void setCards(List<CardModel> cardsToAdd){
        this.content = cardsToAdd;
    }

    public boolean deleteCard(ICardModel cardToDelete){
        return content.remove(cardToDelete);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<CardModel> getCards() {
        return this.content;
    }

    @Override
    public String getStyleRessource() {
        return this.styleRessource;
    }

    @Override
    public String getPictureRessource() {
        return this.pictureRessource;
    }
}
