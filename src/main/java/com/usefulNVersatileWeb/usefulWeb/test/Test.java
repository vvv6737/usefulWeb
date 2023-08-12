package com.usefulNVersatileWeb.usefulWeb.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Test{
    public static void main(String[] args) throws IOException {

        Path relativePath = Paths.get("");

        String currentPath = System.getProperty("user.dir");
        System.out.println("currentPath : " + currentPath);
        String path = relativePath.toAbsolutePath().toString();
        System.out.println("path : " + path);




//        FileReader reader = new FileReader(path + "/../../../Downloads/usefulWebJasyptKey.txt");
//        int ch;
//        String key = "";
//        while ((ch = reader.read()) != -1) {
//            key = key + (char) ch;
//        }
//        System.out.println("key : " + key);

    }

}
