package com.github.adamovichas.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public enum Util {
    UTILS;

    public void removeEmptyValue(Map<String,String> map){
        map.entrySet().removeIf(stringStringEntry -> stringStringEntry.getValue().equals(""));
    }
}
