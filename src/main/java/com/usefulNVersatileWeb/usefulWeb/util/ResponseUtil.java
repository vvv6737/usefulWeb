package com.usefulNVersatileWeb.usefulWeb.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    final static int successCode = 11000;
    final static int failCode = 30000;

    public static HashMap<String, Object> successResponse(String msg) {
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("result", successCode);
        resMap.put("msg", msg);
        return resMap;
    }
    public static HashMap<String, Object> failResponse(int code, String msg) {
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("result", failCode + code);
        resMap.put("msg", msg);
        return resMap;
    }

}
