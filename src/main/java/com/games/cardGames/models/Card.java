package com.games.cardGames.models;

public class Card {

    String id;
    String cardValue;
    Coordinate coords;
    String parentId;

    public Card(String id, String cardValue,Coordinate coords, String parentId) {
        this.id = id;
        this.cardValue = cardValue;
        this.coords = coords;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public Coordinate getCoords() {
        return coords;
    }

    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


}
