package com.islander.islanderjsoup.service;

import com.islander.islanderjsoup.model.Game;
import com.islander.islanderjsoup.model.Statistic;

import java.util.List;

public interface StatisticsService {
    Statistic calculateStatistics(List<Game> games);
}
