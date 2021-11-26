package com.kart;

import com.ParserSettings;

public class KartSettings extends ParserSettings {

    public KartSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "";
        PREFIX = "{CurrentId}";
    }
}