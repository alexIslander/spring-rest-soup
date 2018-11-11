package com.islander.islanderjsoup.model;

public class Prediction {
    Player towin;
    Integer p1Probability;
    Integer p2Probability;
    PointResult setPrediction;
    Double odds;
    boolean setPredictionCorrect;
    boolean predictionCorrect;

    public Player getTowin() {
        return towin;
    }

    public void setTowin(Player towin) {
        this.towin = towin;
    }

    public Integer getP1Probability() {
        return p1Probability;
    }

    public void setP1Probability(Integer p1Probability) {
        this.p1Probability = p1Probability;
    }

    public Integer getP2Probability() {
        return p2Probability;
    }

    public void setP2Probability(Integer p2Probability) {
        this.p2Probability = p2Probability;
    }

    public PointResult getSetPrediction() {
        return setPrediction;
    }

    public void setSetPrediction(PointResult setPrediction) {
        this.setPrediction = setPrediction;
    }

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }

    public boolean isSetPredictionCorrect() {
        return setPredictionCorrect;
    }

    public void setSetPredictionCorrect(boolean setPredictionCorrect) {
        this.setPredictionCorrect = setPredictionCorrect;
    }

    public boolean isPredictionCorrect() {
        return predictionCorrect;
    }

    public void setPredictionCorrect(boolean predictionCorrect) {
        this.predictionCorrect = predictionCorrect;
    }
}
