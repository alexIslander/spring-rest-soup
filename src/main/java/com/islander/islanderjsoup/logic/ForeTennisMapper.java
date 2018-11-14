package com.islander.islanderjsoup.logic;

import com.islander.islanderjsoup.common.IslanderUtils;
import com.islander.islanderjsoup.model.*;
import com.islander.islanderjsoup.model.enumtype.CourtTye;
import com.islander.islanderjsoup.model.enumtype.SurfaceType;
import com.islander.islanderjsoup.model.enumtype.TennisCategoryType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForeTennisMapper {

    public static final String RETIRED = "Ret";
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final IslanderUtils utils;

    public ForeTennisMapper(final IslanderUtils utils) {
        this.utils = utils;
    }

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
                    Elements names = cols.get(1).getAllElements().first().getElementsByTag("a")
                            .get(0).getElementsByTag("span");
                    game.setFirstPlayer(new Player(names.get(0).text()));
                    game.setSecondPlayer(new Player(names.get(2).text()));

                    game.setMatchDate(LocalDateTime.parse(cols.get(1).getElementsByClass("date_match").text(), df));
                    game.setGamePoints(createGamePoints(cols.get(7)));

                    Prediction prediction = new Prediction();
                    prediction.setTowin(isPlayerOne(cols) ? game.getFirstPlayer() : game.getSecondPlayer());
                    if (cols.get(2).hasText()) {
                        prediction.setP1Probability(Integer.valueOf(cols.get(2).text()));
                    }
                    if (cols.get(3).hasText()) {
                        prediction.setP2Probability(Integer.valueOf(cols.get(3).text()));
                    }

                    if (cols.get(6).hasText()) {
                        prediction.setOdds(Double.valueOf(cols.get(6).text()));
                    }
                    if (cols.get(5).hasText()) {
                        prediction.setSetPrediction(createResult(cols.get(5)));
                    }

                    if (cols.get(8).hasText() && !RETIRED.equals(cols.get(8).text())) {
                        List<Integer> p =  utils.stringToIntList(cols.get(8).text());
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

    private boolean isPlayerOne(Elements cols) {
        return "1".equals(cols.get(4).text());
    }

    private boolean isPredictionCorrect(Elements cols, Game game, Prediction prediction) {
        boolean p1Won = game.getOutcome().getPlayerOnePoints().compareTo(
                game.getOutcome().getPlayerTwoPoints()) > 0;
        boolean p2Won= !p1Won;

        boolean p1Pr = prediction.getSetPrediction().getPlayerOnePoints().compareTo(
                prediction.getSetPrediction().getPlayerTwoPoints()) > 0;
        boolean p2Pr = !p1Pr;
        return (isPlayerOne(cols) && p1Won && p1Pr) || (!isPlayerOne(cols) && p2Won && p2Pr);
    }

    private List<PointResult> createGamePoints(Element element) {
        List<PointResult> result = new ArrayList<>();
        Elements pointsContainer = element.getAllElements().first().getElementsByTag("div");
        if (pointsContainer.hasText()) {
            List<Integer> p1Points = utils.stringToIntList(pointsContainer.get(0).text());
            List<Integer> p2Points = utils.stringToIntList(pointsContainer.get(1).text());
            for (int i = 0; i < p1Points.size(); i++) {
                result.add(new PointResult(p1Points.get(i), p2Points.get(i)));
            }
        }
        return result;
    }

    private PointResult createResult(Element col) {
        Elements divs = col.getElementsByTag("div");
        return new PointResult(Integer.valueOf(divs.get(0).text()), Integer.valueOf(divs.get(1).text()));
    }

    public ForeTennisSettings createForeTennisSettings(Document document) {
        List<Integer> years = new ArrayList<>();
        for (int i = 1968; i < LocalDateTime.now().getYear() + 1; i++) {
            years.add(i);
        }
        List<String> types = new ArrayList<>();
        Arrays.asList(TennisCategoryType.values()).forEach(c -> types.add(c.getValue()));

        Map<String, String> names = createTournamentNames(document);

        List<String> surfaces = new ArrayList<>();
        Arrays.asList(SurfaceType.values()).forEach(c -> surfaces.add(c.getValue()));

        List<String> courts = new ArrayList<>();
        Arrays.asList(CourtTye.values()).forEach(c -> courts.add(c.getValue()));

        return new ForeTennisSettings(years, names, types, surfaces, courts);
    }

    public Map<String, String> createTournamentNames(Document document) {
        Elements h2Element = document.getElementsByClass("preds");
        return mapRowsToTournamentNames(h2Element.select("tr"));
    }

    private Map<String, String> mapRowsToTournamentNames(Elements rows) {
        Map<String, String> names = new HashMap<>();
        try {
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() == 5) {
                    String tournamentName = cols.get(0).text();
                    String link = cols.get(0).getAllElements().get(0).getElementsByTag("a")
                            .get(0).attr("href");
                    String tournamentLinkParam = link.split("/")[5];
                    names.put(tournamentName, tournamentLinkParam);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return names;
    }
}
