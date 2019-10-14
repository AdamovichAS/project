package com.github.adamovichas.project.web.util;

import java.util.Map;

public enum Util {
    UTILS;

    public void removeEmptyValue(Map<String,String> map){
        map.entrySet().removeIf(stringStringEntry -> stringStringEntry.getValue().equals(""));
    }
}
