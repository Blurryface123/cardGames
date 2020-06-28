package com.games.cardGames.models;

public class Coordinate {
    String id;
    Long y;
    Long x;

    public Coordinate(String id, Long y, Long x) {
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

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }


}
