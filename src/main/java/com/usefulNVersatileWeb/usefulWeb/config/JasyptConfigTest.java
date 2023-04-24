package com.usefulNVersatileWeb.usefulWeb.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;

public class JasyptConfigTest {

    public static void main(String[] args) {
        aa();

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("23432492384823974289");
        config.setPoolSize("1");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setStringOutputType("base64");
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptor.setConfig(config);

        System.out.println(encryptor.encrypt("jdbc:mysql://localhost:3306/park_web_project_db?autoReconnect=true"));
    }

    @Value("${jasypt.encryptor.bean}")
    private static String a;
    public static String aa() {
        System.out.println(a);
        return a;
    }

}
