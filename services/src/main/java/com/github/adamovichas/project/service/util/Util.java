package com.github.adamovichas.project.service.util;

import java.util.Map;

public class Util implements IUtil{

    @Override
    public void removeEmptyValue(Map<String,String> map){
        map.entrySet().removeIf(stringStringEntry -> stringStringEntry.getValue().equals(""));
    }
}
