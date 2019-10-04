package by.itacademy.jd2.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public enum  Utils {
    UTILS;

    public void removeNullValue(Map<String,String> map){
        map.entrySet().removeIf(stringStringEntry -> stringStringEntry.getValue().equals(""));
    }
}
