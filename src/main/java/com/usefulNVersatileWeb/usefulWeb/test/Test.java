package com.usefulNVersatileWeb.usefulWeb.test;

import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Test{
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String a = StringUtil.sha256("5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5");
        System.out.println(a);
    }
}