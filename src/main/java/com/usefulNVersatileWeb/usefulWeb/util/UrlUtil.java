package com.usefulNVersatileWeb.usefulWeb.util;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

    final static String WEB_PATH = "/view/";

    //url
    public static String url(String urlString, HttpServletRequest request) {
        return WEB_PATH + urlString;
    }
}
