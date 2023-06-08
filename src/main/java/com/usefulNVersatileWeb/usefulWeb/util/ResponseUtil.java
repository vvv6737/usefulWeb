package com.usefulNVersatileWeb.usefulWeb.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> succsessResponse(String msg) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("result", true);
        resMap.put("msg", msg);
        return resMap;
    }
    public static Map<String, Object> failResponse(String msg) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("result", false);
        resMap.put("msg", msg);
        return resMap;
    }

}
