package com.islander.islanderjsoup.model.enumtype;

public enum PlayerType {
    PLAYER1("Player1"),PLAYER2("Player2");

    private String value;

    PlayerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
