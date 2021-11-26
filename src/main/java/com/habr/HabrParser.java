package com.habr;

import com.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HabrParser implements Parser<ArrayList<String>> {

    //@Override
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<String>();
        // получение списка названий статей
        Elements articles = document.getElementsByTag("article");
        for (Element article :  articles) {
            Element e = article.getElementsByTag("h2").first();
            list.add(e.text());
        }
        return list;
    }

}


/*
<h2 class="tm-article-snippet__title tm-article-snippet__title_h2"><a href="/ru/company/intel/blog/589509/"
class="tm-article-snippet__title-link" data-article-link=""><span>Микропроцессору Intel 4004 — 50 лет</span></a></h2>
 */