package com.islander.islanderjsoup.logic;

import com.islander.islanderjsoup.model.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForeTennisMapper {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public Tournament createTournament(Document document) {
        return mapTournament(document);
    }

    private Tournament mapTournament(Document document) {
        Elements h2Element = document.getElementsByClass("preds");
        Elements content = document.getElementsByClass("content");
        Element title = content.get(0).getElementsByTag("div").first().getElementsByTag("h1").first();


        Tournament tournament = new Tournament();
        tournament.setName(title.text());
        // TODO set date
        tournament.setGames(mapRowsToGames(h2Element.select("tr")));

        return tournament;
    }

    private List<Game> mapRowsToGames(Elements rows) {
        List<Game> games = new ArrayList<>();
        try {
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() == 9) {
                    System.out.println(cols.text());
                    Game game = new Game();
                    game.setRound(cols.get(0).getAllElements().first().getElementsByClass("largeOnly").text());
                    Elements names = cols.get(1).getAllElements().first().getElementsByTag("a").get(0).getElementsByTag("span");
                    game.setFirstPlayer(new Player(names.get(0).text()));
                    game.setSecondPlayer(new Player(names.get(2).text()));

                    game.setMatchDate(LocalDateTime.parse(cols.get(1).getElementsByClass("date_match").text(), df));
                    game.setGamePoints(createGamePoints(cols.get(7)));

                    Prediction prediction = new Prediction();
                    prediction.setTowin("1".equals(cols.get(4).text()) ? game.getFirstPlayer() : game.getSecondPlayer());
                    if (cols.get(6).hasText()) {
                        prediction.setOdds(Double.valueOf(cols.get(6).text()));
                    }
                    if (cols.get(5).hasText()) {
                        prediction.setSetPrediction(createResult(cols.get(5)));
                    }

                    if (cols.get(8).hasText() && !"Ret".equals(cols.get(8).text())) {
                        List<Integer> p =  stringToIntList(cols.get(8).text());
                        game.setOutcome(new PointResult(p.get(0) , p.get(1)));
                    }

                    if (game.getOutcome() != null && prediction.getSetPrediction() != null) {
                        prediction.setPredictionCorrect(isPredictionCorrect(cols, game, prediction));
                        prediction.setSetPredictionCorrect(prediction.getSetPrediction().equals(game.getOutcome()));
                    }
                    game.setPrediction(prediction);
                    games.add(game);
                }
            }
        } catch (NumberFormatException nfe) {
            // TODO exception handling
            System.out.println(nfe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return games;
    }

    private boolean isPredictionCorrect(Elements cols, Game game, Prediction prediction) {
        boolean p1Won = game.getOutcome().getPlayerOnePoints().compareTo(
                game.getOutcome().getPlayerTwoPoints()) > 0;
        boolean p2Won= !p1Won;

        boolean p1Pr = prediction.getSetPrediction().getPlayerOnePoints().compareTo(
                prediction.getSetPrediction().getPlayerTwoPoints()) > 0;
        boolean p2Pr = !p1Pr;
        return ("1".equals(cols.get(4).text()) && p1Won && p1Pr) ||
                ("2".equals(cols.get(4).text()) && p2Won && p2Pr);
    }

    private List<PointResult> createGamePoints(Element element) {
        List<PointResult> result = new ArrayList<>();
        Elements pointsContainer = element.getAllElements().first().getElementsByTag("div");
        if (pointsContainer.hasText()) {
            List<Integer> p1Points = stringToIntList(pointsContainer.get(0).text());
            List<Integer> p2Points = stringToIntList(pointsContainer.get(1).text());
            for (int i = 0; i < p1Points.size(); i++) {
                result.add(new PointResult(p1Points.get(i), p2Points.get(i)));
            }
        }
        return result;
    }

    private List<Integer> stringToIntList(String input) {
        return Arrays.stream(input.split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private PointResult createResult(Element col) {
        Elements divs = col.getElementsByTag("div");
        return new PointResult(Integer.valueOf(divs.get(0).text()), Integer.valueOf(divs.get(1).text()));
    }

}
