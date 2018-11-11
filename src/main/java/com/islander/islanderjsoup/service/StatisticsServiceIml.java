package com.islander.islanderjsoup.service;

import com.islander.islanderjsoup.common.IslanderUtils;
import com.islander.islanderjsoup.logic.ForeTennisMapper;
import com.islander.islanderjsoup.model.Game;
import com.islander.islanderjsoup.model.PointResult;
import com.islander.islanderjsoup.model.enumtype.PointResultType;
import com.islander.islanderjsoup.model.Statistic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceIml implements StatisticsService {


    public static final int FIRST = 0;
    public static final int SECOND = 1;
    private final IslanderUtils utils;

    public StatisticsServiceIml(final IslanderUtils utils) {
        this.utils = utils;
    }

    @Override
    public Statistic calculateStatistics(List<Game> games) {
        Statistic stat = new Statistic();

        Predicate<Game> invalidGamePredicate = g -> g.getOutcome() == null || g.getPrediction() == null || g.getPrediction().getP1Probability() == null
                || g.getPrediction().getP2Probability() == null || g.getPrediction().getSetPrediction() == null;
        Predicate<Game> validGamePredicate = g -> g.getOutcome() != null && g.getPrediction() != null && g.getPrediction().getP1Probability() != null
                && g.getPrediction().getP2Probability() != null && g.getPrediction().getSetPrediction() != null;

        stat.setInvalidGames(((Long)games.stream().filter(invalidGamePredicate).count()).intValue());

        System.out.println(games.size());
        List<Game> validGames = games.stream().filter(validGamePredicate).collect(Collectors.toList());
        System.out.println(validGames.size());

        PointResultType.getPointStatisticsMap().entrySet().stream().forEach(pMap ->
            stat.getPointStatistics().put(
                    pMap.getKey(), countSetResult(validGames, pMap.getValue(), FIRST).doubleValue())
        );

        stat.setValidGameCount(validGames.size());
        return stat;
    }

    /**
     * This method counts the occurrence of the set result in the given set of the given games on the favorite player side.
     * Eg.: 6 4 present 5 times, 7 5 present 7 times, etc.
     * @param games all games in a tournament
     * @param resultVariations 6 4 and 4 6 usage depending on which player is in favor (P1 or P2)
     * @param setNumber where result is counted, value can first or second set
     * @return map of result(6 4 - 5, 7 5 - 7, etc.)
     */
    private Long countSetResult(List<Game> games, PointResultType[] resultVariations, Integer setNumber) {
        return games.stream()
                .filter(g -> isP1Favorite(g) ? gameIsOnPlayerFavor(FIRST, g.getGamePoints().get(setNumber), resultVariations) :
                        gameIsOnPlayerFavor(SECOND, g.getGamePoints().get(setNumber), resultVariations))
                .count();
    }

    /**
     * This method compares the game result in the given set for the favorite player.
     * P1 is favorite, set result is compared with 6 4. P2 is favorite, set result is compared with 4 6.
     * @param playerNumber order of the player(first, second(
     * @param pointResult the result of the given set
     * @param resultVariations result variations (Eg.:6 4, 4 6)
     * @return is the favorite player result equals with parameters
     */
    private boolean gameIsOnPlayerFavor(int playerNumber, PointResult pointResult, PointResultType[] resultVariations) {
        return pointResult.equals(new PointResult(utils.stringToIntList(resultVariations[playerNumber].value())));
    }

    private boolean isP1Favorite(Game g) {
        return g.getPrediction().getP1Probability().compareTo(g.getPrediction().getP2Probability()) > 0;
    }
}
