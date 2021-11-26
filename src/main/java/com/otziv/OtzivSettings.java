package com.otziv;

import com.ParserSettings;

public class OtzivSettings extends ParserSettings {

    public OtzivSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://nanegative.ru/internet-magaziny";
        PREFIX = "{CurrentId}";
    }
}