package com.islander.islanderjsoup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForeTennisSettings {
    List<Integer> years = new ArrayList<>();
    Map<String, String> names = new HashMap<>();
    List<String> types = new ArrayList<>();
    List<String> surfaces = new ArrayList<>();
    List<String> courts = new ArrayList<>();

    public ForeTennisSettings(List<Integer> years, Map<String, String> names, List<String> types, List<String> surfaces, List<String> courts) {
        this.years = years;
        this.names = names;
        this.types = types;
        this.surfaces = surfaces;
        this.courts = courts;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public Map<String, String> getNames() {
        return names;
    }

    public void setNames(Map<String, String> names) {
        this.names = names;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getSurfaces() {
        return surfaces;
    }

    public void setSurfaces(List<String> surfaces) {
        this.surfaces = surfaces;
    }

    public List<String> getCourts() {
        return courts;
    }

    public void setCourts(List<String> courts) {
        this.courts = courts;
    }
}
