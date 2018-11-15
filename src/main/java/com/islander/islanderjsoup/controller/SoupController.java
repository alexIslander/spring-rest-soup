package com.islander.islanderjsoup.controller;

import com.islander.islanderjsoup.model.ForeTennisSettings;
import com.islander.islanderjsoup.model.Tournament;
import com.islander.islanderjsoup.service.EvenOddService;
import com.islander.islanderjsoup.service.ForeTennisService;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ws.rs.QueryParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SoupController {

    final static Logger LOGGER = LoggerFactory.getLogger(SoupController.class);

    public static final String BASE_URL = "https://www.foretennis.com";

    private final EvenOddService evenOddService;
    private final ForeTennisService foreTennisService;

    // constructor
    public SoupController(final EvenOddService evenOddService,
                          final ForeTennisService foreTennisService) {
        this.evenOddService = evenOddService;
        this.foreTennisService = foreTennisService;
    }

    @GetMapping("/soup")
    public String soup() {
        String url = "https://www.openml.org/t/31";
        LOGGER.info("Called soup() with url {}.", url);
        Elements h2Element = null;
        try {
            Document doc = Jsoup.connect(url).get();
            Element parentElement = doc.select("div#detail").first();
            h2Element = parentElement.child(1).select("h2");
            System.out.println(h2Element.text());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return h2Element.text();
    }

    @GetMapping("/settings")
    public ForeTennisSettings tennisSettings() {
        String urlFull = BASE_URL.concat("/").concat("tournaments").concat("/").concat("atp")
                .concat("/").concat(String.valueOf(LocalDateTime.now().getYear()));
        ForeTennisSettings settings = null;

        try {
            Document doc = Jsoup.connect(urlFull).get();
            settings = foreTennisService.createSettings(doc);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return settings;
    }

    @GetMapping("/settings/tournamentNames")
    public Map<String, String> tennisNames(@QueryParam("year") final String year) {
        String urlFull = BASE_URL.concat("/").concat("tournaments").concat("/").concat("atp")
                .concat("/").concat(year);
        Map<String, String> settings = null;

        try {
            Document doc = Jsoup.connect(urlFull).get();
            settings = foreTennisService.getTournamentNames(doc);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return settings;
    }


    @GetMapping("/tournamentSettings/{atp_wta}/{year}")
    public List<Tournament> tennisSettings(@QueryParam("atp_wta") final String atp_wta,
                                                    @QueryParam("year") final String year) {
        String urlFull = BASE_URL;
        if (!StringUtil.isBlank(atp_wta) && !StringUtil.isBlank(year)) {
            urlFull = urlFull.concat(atp_wta).concat("/").concat(year);
        }

        Map<String, List<String>> tennisSettings = new HashMap<>();
        List<Tournament> tournaments = null;
        try {
            Document doc = Jsoup.connect(urlFull).get();
            // TODO
//            tournament = foreTennisService.createTournament(doc);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return tournaments;
    }

    @GetMapping("/tennis")
    public Tournament tennis(@QueryParam("url") final String url) {
        String urlFull = BASE_URL;
        if (!StringUtil.isBlank(url)) {
            urlFull = urlFull.concat(url);
        }
        Elements h2Element = null;
        List<String> result = new ArrayList<>();
        Tournament tournament = null;
        try {
            Document doc = Jsoup.connect(urlFull).get();

            tournament = foreTennisService.createTournament(doc);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return tournament;
    }

    @GetMapping("/tennisStat")
    public Tournament tennisStat(@QueryParam("url") final String url) {
        String urlFull = BASE_URL;
        if (!StringUtil.isBlank(url)) {
            urlFull = urlFull.concat(url);
        }
        Elements h2Element = null;
        List<String> result = new ArrayList<>();
        Tournament tournament = null;
        try {
            Document doc = Jsoup.connect(urlFull).get();

            tournament = foreTennisService.createTournament(doc);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        tournament.setGames(null);
        return tournament;
    }

    @GetMapping("/tennisall")
    public String tennisall(@QueryParam("url") final String url) {
        String urlFull = BASE_URL;
        if (!StringUtil.isBlank(url)) {
            urlFull = urlFull.concat(url);
        }

        Elements h2Element = null;
        String html = null;
        try {
            html = Jsoup.connect(urlFull).get().html();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return html.toString();
    }

    @GetMapping("/validate")
    public String isEvenOrOdd(
            @RequestParam("number") Integer number) {
        return evenOddService.isEvenOrOdd(number);
    }
}
