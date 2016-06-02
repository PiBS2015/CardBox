package net.ict_campus.hoferc_burkharta.cardbox.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Burkharta on 01.06.2016.
 */
public class SetModel implements ISetModel {
    private String name;
    private List<ICardModel> content;
    //Not used atm.
    private String styleRessource;
    private String pictureRessource;

    public SetModel(String name){
        this.name = name;
        this.content = new ArrayList<>();
    }

    SetModel(String name, Collection<ICardModel> content){
        this.content = new ArrayList<>(content);
        this.name = name;
    }

    public void addCard(ICardModel cardToAdd){
        content.add(cardToAdd);
    }

    public boolean deleteCard(ICardModel cardToDelete){
        return content.remove(cardToDelete);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<ICardModel> getCards() {
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
