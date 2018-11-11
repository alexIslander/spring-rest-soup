package com.islander.islanderjsoup.model;

import com.islander.islanderjsoup.model.enumtype.PlayerType;

public class Player {
    String name;
    Integer atpRank;
    String url;
    PlayerType type;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAtpRank() {
        return atpRank;
    }

    public void setAtpRank(Integer atpRank) {
        this.atpRank = atpRank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
}
