package com.islander.islanderjsoup.model.enumtype;

public enum TennisCategoryType {
    ATP("ATP"), WTA("WTA");

    private String value;

    TennisCategoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
