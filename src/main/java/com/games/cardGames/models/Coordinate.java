package com.games.cardGames.models;

public class Coordinate {
    String id;
    int y;
    int x;

    public Coordinate(String id, int y, int x) {
        this.id = id;
        this.y = y;
        this.x = x;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


}
