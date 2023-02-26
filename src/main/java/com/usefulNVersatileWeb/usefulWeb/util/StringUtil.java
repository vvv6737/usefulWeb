package com.usefulNVersatileWeb.usefulWeb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {

    public static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        return byteToHexString(md.digest());
    }

    /**
     * 바이트를 헥스값으로 변환한다.
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b:  bytes) {
            builder.append(String.format("%02x",  b));
        }
        return builder.toString();
    }

    public static Boolean strNullCheck(String text) {
        if(text == "" || text == null) {
            return false;
        }
        return true;
    }

    public static Boolean objNullCheck(Object obj) {
        if(obj == "" || obj == null) {
            return false;
        }
        return true;
    }

//    map을 queryString으로 변환한다.
    public static String formatQueryParams(Map<String, Object> params) {
        return params.entrySet().stream().map(p -> p.getKey() + "=" + p.getValue())
                .reduce((p1, p2) -> p1 + "&" + p2)
                .map(s -> "?" + s)
                .orElse("");
    }
}
