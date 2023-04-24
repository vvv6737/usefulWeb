package com.usefulNVersatileWeb.usefulWeb.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptConfigTest {

    public static void main(String[] args) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("23432492384823974289");
        config.setPoolSize("1");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setStringOutputType("base64");
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptor.setConfig(config);

//        System.out.println(encryptor.encrypt("jdbc:mysql://localhost:3306/park_web_project_db?autoReconnect=true"));
//        System.out.println(encryptor.decrypt("VdNr7yG/X/Z1e1bYF8sBgnNWHvY6300uHTvRWtIuGDUvRIgveMdxeN/Z5clZ4CGM7PMqg5qDG9jL56GClC1CvdWM2cFJrk0RmqQRgY5kR5U="));
    }
}
