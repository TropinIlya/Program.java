package com.kart;

import com.Parser;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class KartParser implements Parser<ArrayList<String>> {

    //@Override
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<String>();

                list.add("успешно");

        return list;
    }

}
//<img class="MMImage-Origin" src="https://modelist-konstruktor.com/wp-content/uploads/2020/03/2-54.jpg">