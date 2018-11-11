package com.islander.islanderjsoup.service;

import com.islander.islanderjsoup.model.Tournament;
import org.jsoup.nodes.Document;

public interface ForeTennisService {
    Tournament createTournament(Document document);
}
