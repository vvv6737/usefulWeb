package com.usefulNVersatileWeb.usefulWeb.test;

import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Test{
    public static void main(String[] args) throws JSONException {

        String a = "'{\"0\":{\"test1\":123,\"fff\":\"567\"},\"1\":{\"test1\":456,\"fff\":\"fsdakfs\"}}'";
        JSONObject jObj = new JSONObject(a);

        System.out.println(a);
    }
}