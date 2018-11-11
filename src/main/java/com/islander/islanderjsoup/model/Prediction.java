package com.islander.islanderjsoup.model;

public class Prediction {
    Player towin;
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
