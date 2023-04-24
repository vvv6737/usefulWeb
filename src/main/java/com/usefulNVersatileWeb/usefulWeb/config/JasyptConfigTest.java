package com.usefulNVersatileWeb.usefulWeb.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class JasyptConfigTest {
    public static void main(String[] args) {

//        String url = "jdbc:postgresql://localhost:5432/postgres";
        String url = "jdbc:mysql://localhost:3306/park_web_project_db?useUnicode=true&characterEncoding=utf-8";
        String username = "jeong_hoon_park";
        String password = "park12345";

        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

//        String deUrl = jasyptDecryt(url);
//        String deUsername = jasyptDecryt(username);
//        String dePassword = jasyptDecryt(password);

        System.out.println("encryptUrl : " + encryptUrl);
        System.out.println("encryptUsername : " + encryptUsername);
        System.out.println("encryptPassword : " + encryptPassword);

//        System.out.println("deUrl : " + deUrl);
//        System.out.println("deUsername : " + deUsername);
//        System.out.println("dePassword : " + dePassword);

//        Assertions.assertThat(url).isEqualTo(jasyptDecryt(encryptUrl));
    }

    private static String jasyptEncrypt(String input) {
        String key = "5678";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

    private static String jasyptDecryt(String input){
        String key = "5678";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }
}
