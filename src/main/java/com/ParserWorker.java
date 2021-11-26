package com;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.kart.JsoupDownloadImagesExample.downloadImage;

public class ParserWorker<T> {
    Parser<T> parser;
    ParserSettings parserSettings;

    public ParserWorker(Parser parser) {
        this.parser = parser;
    }


    public Parser<T> getParser() {
        return parser;
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public ParserSettings getParserSettings() {
        return parserSettings;
    }

    HtmlLoader loader;

    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
        loader = new HtmlLoader(parserSettings);
    }

    boolean isActive;

    public void Start() throws IOException {
        isActive = true;
        Worker();
    }

    public void StartOt() throws IOException {
        isActive = true;
        WorkerOt();
    }

    public void StartT() throws IOException {
        isActive = true;
        WorkerT();
    }

    public void StartK() throws IOException {
        isActive = true;
        WorkerK();
    }

    public void Abort() {
        isActive = false;
    }

    public void AbortOt() {
        isActive = false;
    }

    public void AbortT() {
        isActive = false;
    }

    public void AbortK() {
        isActive = false;
    }

    ArrayList<OnNewDataHandler> onNewDataList = new ArrayList<OnNewDataHandler>();
    ArrayList<OnCompleted> onCompletedList = new ArrayList<OnCompleted>();


    private void Worker() throws IOException {
        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            Document document = loader.GetSourceByPageId(i);
            T result = parser.Parse(document);
            onNewDataList.get(0).OnNewData(this,result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

    private void WorkerOt() throws IOException {

        ArrayList<String> page = new ArrayList<String>();
        int j = 1;
        while (j <= 8)
        {
        Document document1 = loader.GetSourceByPage("https://nanegative.ru/internet-magaziny?page="+Integer.toString(j));
            Elements articles = document1.getElementsByClass("find-list-box");
            for (Element article :  articles) {
                Element e = article.getElementsByTag("a").first();
                page.add(e.absUrl("href"));
            }
        j++;
        }

        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            Document document = loader.GetSourceByPage(page.get(i));
            T result = parser.Parse(document);
            onNewDataList.get(0).OnNewData(this,result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

    private void WorkerT() throws IOException {

        ArrayList<String> page = new ArrayList<String>();
        int j = 1;
        while (j <= 8)
        {
            Document document1 = loader.GetSourceByPage("https://kirov.leroymerlin.ru/catalogue/iskusstvennye-eli/?page="+Integer.toString(j));
            Elements articles = document1.getElementsByClass("phytpj4_plp LargeCard");
            for (Element article :  articles) {
                Element e = article.getElementsByTag("a").first();
                page.add(e.absUrl("href"));
            }
            j++;
        }

        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            Document document = loader.GetSourceByPage(page.get(i));
            //+"/#nav-reviews"
            T result = parser.Parse(document);
            onNewDataList.get(0).OnNewData(this,result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

    private void WorkerK() throws IOException {

        ArrayList<String> page = new ArrayList<String>();
        Document document1 = loader.GetSourceByPage("https://yandex.ru/images/search?text=%D0%B1%D1%80%D0%BE%D0%BD%D0%B5%D0%BF%D0%B0%D0%BB%D1%83%D0%B1%D0%BD%D1%8B%D0%B5%20%D0%BA%D1%80%D0%B5%D0%B9%D1%81%D0%B5%D1%80%D0%B0");
        Elements articles = document1.getElementsByClass("serp-item__thumb justifier__thumb");
        String s;
        for (Element article :  articles) {
                s = article.attr("src");
                page.add(s);
        }



        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }

            downloadImage(page.get(i),i);
            //            Document document = loader.GetSourceByPage(page.get(i));
            //            //+"/#nav-reviews

            T result = parser.Parse(document1);
            onNewDataList.get(0).OnNewData(this,result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

    public interface OnNewDataHandler<T> {
        void OnNewData(Object sender, T e);
    }

    public interface OnCompleted {
        void OnCompleted(Object sender);
    }

}

//<img class="serp-item__thumb justifier__thumb" data-thumb="//avatars.mds.yandex.net/i?id=cdc9c7a7df6ac17ff66e9d5462699452-3981791-images-thumbs&amp;n=13" data-error-handler="serpItemError" alt="Бронепалубные крейсера. " src="//avatars.mds.yandex.net/i?id=cdc9c7a7df6ac17ff66e9d5462699452-3981791-images-thumbs&amp;n=13" style="height: 182px; width: 244px;">
