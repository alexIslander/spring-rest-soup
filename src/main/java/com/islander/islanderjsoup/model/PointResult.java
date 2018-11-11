package com.islander.islanderjsoup.model;

import java.util.Objects;

public class PointResult {
    Integer playerOnePoints;
    Integer playerTwoPoints;

    public PointResult(Integer playerOnePoints, Integer playerTwoPoints) {
        this.playerOnePoints = playerOnePoints;
        this.playerTwoPoints = playerTwoPoints;
    }

    public Integer getPlayerOnePoints() {
        return playerOnePoints;
    }

    public void setPlayerOnePoints(Integer playerOnePoints) {
        this.playerOnePoints = playerOnePoints;
    }

    public Integer getPlayerTwoPoints() {
        return playerTwoPoints;
    }

    public void setPlayerTwoPoints(Integer playerTwoPoints) {
        this.playerTwoPoints = playerTwoPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointResult that = (PointResult) o;
        return Objects.equals(playerOnePoints, that.playerOnePoints) &&
                Objects.equals(playerTwoPoints, that.playerTwoPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerOnePoints, playerTwoPoints);
    }
}
