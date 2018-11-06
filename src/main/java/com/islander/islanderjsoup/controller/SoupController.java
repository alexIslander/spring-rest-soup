package com.islander.islanderjsoup.controller;

import com.islander.islanderjsoup.service.EvenOddService;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
public class SoupController {

    @Autowired
    private final EvenOddService evenOddService;

    // constructor
    public SoupController(final EvenOddService evenOddService) {
        this.evenOddService = evenOddService;
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
    public String tennis(@RequestParam("url") String url) {
        if (StringUtil.isBlank(url)) {
            url = "https://www.foretennis.com/";
        }
        Elements h2Element = null;
        try {
            Document doc = Jsoup.connect(url).get();
            h2Element = doc.getElementsMatchingText("Last predictions");
            System.out.println(h2Element.text());
        } catch (Exception e) {

        }


        return h2Element.text();
    }



    @GetMapping("/tennisall")
    public String tennisall(@RequestParam("url") String url) {
        if (StringUtil.isBlank(url)) {
            url = "https://www.foretennis.com/";
        }
        Elements h2Element = null;
        String html = null;
        try {
            html = Jsoup.connect(url).get().html();

        } catch (Exception e) {

        }
        return html.toString();
    }

    @GetMapping("/validate")
    public String isEvenOrOdd(
            @RequestParam("number") Integer number) {
        return evenOddService.isEvenOrOdd(number);
    }
}
