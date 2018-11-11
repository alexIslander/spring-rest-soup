package com.islander.islanderjsoup.service;

import com.islander.islanderjsoup.logic.ForeTennisMapper;
import com.islander.islanderjsoup.model.Tournament;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ForeTennisServiceImpl implements ForeTennisService{

    private final ForeTennisMapper foreTennisMapper;
    private final StatisticsService statisticsService;

    public ForeTennisServiceImpl(final ForeTennisMapper foreTennisMapper,
                                 final StatisticsService statisticsService) {
        this.foreTennisMapper = foreTennisMapper;
        this.statisticsService = statisticsService;
    }

    @Override
    public Tournament createTournament(Document document) {
        Tournament tournament = foreTennisMapper.createTournament(document);
        tournament.setStatistic(statisticsService.calculateStatistics(tournament.getGames()));
        return tournament;
    }
}
