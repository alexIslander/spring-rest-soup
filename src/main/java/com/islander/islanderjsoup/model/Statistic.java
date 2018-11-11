package com.islander.islanderjsoup.model;

import java.util.HashMap;
import java.util.Map;

public class Statistic {
    Map<String, Double> pointStatistics = new HashMap<>();
    Integer validGameCount;
    Integer invalidGames;

    public Map<String, Double> getPointStatistics() {
        return pointStatistics;
    }

    public void setPointStatistics(Map<String, Double> pointStatistics) {
        this.pointStatistics = pointStatistics;
    }

    public Integer getValidGameCount() {
        return validGameCount;
    }

    public void setValidGameCount(Integer validGameCount) {
        this.validGameCount = validGameCount;
    }

    public Integer getInvalidGames() {
        return invalidGames;
    }

    public void setInvalidGames(Integer invalidGames) {
        this.invalidGames = invalidGames;
    }
}
