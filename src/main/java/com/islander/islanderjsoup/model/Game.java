package com.islander.islanderjsoup.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {
    // TODO enum
//    RoundType round;
    String round;
    Player firstPlayer;
    Player SecondPlayer;
    List<PointResult> gamePoints = new ArrayList<>();
    LocalDateTime matchDate;
    Prediction prediction;
    PointResult outcome;

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return SecondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        SecondPlayer = secondPlayer;
    }

    public List<PointResult> getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(List<PointResult> gamePoints) {
        this.gamePoints = gamePoints;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    public PointResult getOutcome() {
        return outcome;
    }

    public void setOutcome(PointResult outcome) {
        this.outcome = outcome;
    }
}
