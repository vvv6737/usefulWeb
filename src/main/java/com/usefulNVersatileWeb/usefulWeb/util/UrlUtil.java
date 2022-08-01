package com.usefulNVersatileWeb.usefulWeb.util;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

    final static String WEB_PATH = "/view/";

    //url
    public static String url(String urlString, HttpServletRequest request) {
//        System.out.println(ipView(request));
        return WEB_PATH + urlString;
    }

    //ip주소 가져오기
    public static String ipView(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
//            System.out.println("Proxy-Client-IP" + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
//            System.out.println("WL-Proxy-Client-IP" + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
//            System.out.println("HTTP_CLIENT_IP" + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//            System.out.println("HTTP_X_FORWARDED_FOR" + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
//            System.out.println(ip);
        }
        return ip;
    }
}
