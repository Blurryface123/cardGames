package com.games.cardGames.models;

public class Card {

    String id;
    String cardValue;
    Coordinate coords;

    public Card(String id, String cardValue,Coordinate coords) {
        this.id = id;
        this.cardValue = cardValue;
        this.coords = coords;
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


}
