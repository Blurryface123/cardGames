package com.games.cardGames.models;

public class Card {

    String id;
    String cardValue;

    public Card(String id, String cardValue) {
        this.id = id;
        this.cardValue = cardValue;
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

}
