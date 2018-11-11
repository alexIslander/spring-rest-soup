package com.islander.islanderjsoup.controller;

import com.islander.islanderjsoup.model.Tournament;
import com.islander.islanderjsoup.service.EvenOddService;
import com.islander.islanderjsoup.service.ForeTennisService;
import org.jsoup.helper.StringUtil;
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
import java.util.ArrayList;
import java.util.List;

@RestController
public class SoupController {

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
        Elements h2Element = null;
        try {
            Document doc = Jsoup.connect(url).get();
            Element parentElement = doc.select("div#detail").first();
            h2Element = parentElement.child(1).select("h2");
            System.out.println(h2Element.text());
        } catch (Exception e) {

        }
        return h2Element.text();
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
            System.out.println(e.getStackTrace());
        }

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
            System.out.println(e.getStackTrace());
        }
        return html.toString();
    }

    @GetMapping("/validate")
    public String isEvenOrOdd(
            @RequestParam("number") Integer number) {
        return evenOddService.isEvenOrOdd(number);
    }
}
