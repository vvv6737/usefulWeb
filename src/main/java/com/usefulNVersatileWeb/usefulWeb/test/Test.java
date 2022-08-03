package com.usefulNVersatileWeb.usefulWeb.test;

import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Test{
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String a = StringUtil.sha256("");
        System.out.println(a.length());
    }
}