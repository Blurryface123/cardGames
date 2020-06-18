package com.games.cardGames.models;

public class Card {

    String id;
    String cardValue;
    String cardImg;

    public Card(String id, String cardValue,String cardImg) {
        this.id = id;
        this.cardValue = cardValue;
        this.cardImg = cardImg;
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

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }


}
