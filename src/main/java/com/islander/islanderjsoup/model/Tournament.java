package com.islander.islanderjsoup.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    String name;
    Attirbutes attirbutes;
    LocalDate year;
    List<Game> games = new ArrayList<>();
    Statistic statistic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
