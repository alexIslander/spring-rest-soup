package com.islander.islanderjsoup.model.enumtype;

public enum CourtTye {
    OUTDOOR("Outdoor"), INDOOR("Indoor");

    private String value;

    CourtTye(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
