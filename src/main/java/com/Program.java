package com;

import com.kart.KartParser;
import com.kart.KartSettings;

import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        ParserWorker<ArrayList<String>>  parser = new ParserWorker<ArrayList<String>>(new KartParser());
        // задайте значения переменным start и end
        int start=1;
        int end=15;
        parser.setParserSettings(new KartSettings(start, end));
        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewData());
        parser.StartK();
        Thread.sleep(10000);
        parser.AbortK();
    }


    static class NewData implements ParserWorker.OnNewDataHandler<ArrayList<String>> {

        //@Override
        public void OnNewData(Object sender, ArrayList<String> args) {
            for (String s : args) {
                System.out.println(s);
            }
        }
    }

    static class Completed implements ParserWorker.OnCompleted {

        //@Override
        public void OnCompleted(Object sender) {
            System.out.println("Загрузка закончена");
        }
    }

}