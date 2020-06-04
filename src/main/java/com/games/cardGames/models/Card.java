package com.games.cardGames.models;

public class Card {

    int id;
    String cardValue;

    public Card(int id, String cardValue) {
        this.id = id;
        this.cardValue = cardValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

}
