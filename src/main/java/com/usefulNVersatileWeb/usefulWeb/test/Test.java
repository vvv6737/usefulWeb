package com.usefulNVersatileWeb.usefulWeb.test;

import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class Test{
    public static void main(String[] args) {
        Map<String, Object> tMap = new HashMap<>();

        tMap.put("아아","이잉");
        tMap.put("어어","어엉");
        tMap.put("우우","우웅");

        System.out.println("tMap : " + tMap);

        String str = StringUtil.formatQueryParams(tMap) ;

        System.out.println("str : " + str);
    }
}