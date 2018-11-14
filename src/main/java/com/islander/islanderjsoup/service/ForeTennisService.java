package com.islander.islanderjsoup.service;

import com.islander.islanderjsoup.model.ForeTennisSettings;
import com.islander.islanderjsoup.model.Tournament;
import org.jsoup.nodes.Document;

import java.util.Map;

public interface ForeTennisService {
    Tournament createTournament(Document document);

    ForeTennisSettings createSettings(Document document);

    Map<String, String> getTournamentNames(Document document);

}
