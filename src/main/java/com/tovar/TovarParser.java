package com.tovar;

import com.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class TovarParser implements Parser<ArrayList<String>> {
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<String>();

        Element name = document.getElementsByTag("uc-pdp-card-ga-enriched").first();
        Element e1 = name.getElementsByTag("h1").last();
        list.add(e1.text());


        Elements articles = document.getElementsByTag("uc-prp-review-card");
        for (Element article :  articles) {
            list.add(article.text());
        }
        return list;
    }
}
