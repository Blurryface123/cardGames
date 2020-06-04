package com.games.cardGames.models;

public class Coordinate {
    int id;
    int y;
    int x;

    public Coordinate(int id, int y, int x) {
        this.id = id;
        this.y = y;
        this.x = x;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
