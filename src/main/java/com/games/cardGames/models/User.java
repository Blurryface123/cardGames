package com.games.cardGames.models;

import java.util.UUID;

public class User {

    String id;
    String username;
    String password;
    Integer room;


    public User(String id, String username, String password, Integer room) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.room = room;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

}
