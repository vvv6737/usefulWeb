package com.usefulNVersatileWeb.usefulWeb.test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test{
    public static void main(String[] args) throws IOException {

        Path relativePath = Paths.get("");

        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);


        String path = relativePath.toAbsolutePath().toString();
        FileReader reader = new FileReader(path + "/../../../Downloads/usefulWebJasyptKey.txt");
        int ch;
        String key = "";
        while ((ch = reader.read()) != -1) {
            key = key + (char) ch;
        }
        System.out.println("key : " + key);


    }

}
