package com.tovar;

import com.ParserSettings;

public class TovarSettings extends ParserSettings {

    public TovarSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://nanegative.ru/internet-magaziny?page=";
        PREFIX = "{CurrentId}";
    }
}
