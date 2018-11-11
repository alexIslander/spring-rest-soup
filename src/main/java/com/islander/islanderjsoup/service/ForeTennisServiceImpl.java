package com.islander.islanderjsoup.service;

import com.islander.islanderjsoup.logic.ForeTennisMapper;
import com.islander.islanderjsoup.model.Tournament;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ForeTennisServiceImpl implements ForeTennisService{

    private final ForeTennisMapper foreTennisMapper;

    public ForeTennisServiceImpl(ForeTennisMapper foreTennisMapper) {
        this.foreTennisMapper = foreTennisMapper;
    }

    @Override
    public Tournament createTournament(Document document) {
        return foreTennisMapper.createTournament(document);
    }
}
