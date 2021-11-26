package com.otziv;

import com.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class OtzivParser implements Parser<ArrayList<String>> {

    //@Override
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<String>();
        // получение списка названий статей
        Elements articles = document.getElementsByClass("reviewers-box");
        for (Element article :  articles) {
            Element e = article.getElementsByTag("tbody").last();
            list.add(e.text());
        }
        return list;
    }

}
