package com.github.adamovichas.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {

    private Util util = Util.UTILS;

    @Test
    public void removeEmptyValue(){
        Map<String,String>map = new HashMap<>();
        map.put("login","login");
        map.put("password","password");
        map.put("age","25");
        util.removeEmptyValue(map);
        assertEquals(3,map.size());
        map.put("empty","");
        map.put("emptyTwo","");
        util.removeEmptyValue(map);
        assertEquals(3,map.size());
    }
}
