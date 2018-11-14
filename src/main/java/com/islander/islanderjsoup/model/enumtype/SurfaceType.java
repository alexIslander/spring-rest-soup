package com.islander.islanderjsoup.model.enumtype;

public enum SurfaceType {
    CLAY("Clay"), HARD("Hard"), GRASS("Grass");

    private String value;

    SurfaceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
